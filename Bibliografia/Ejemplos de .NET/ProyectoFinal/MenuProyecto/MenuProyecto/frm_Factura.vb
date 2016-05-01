Public Class frm_Factura

    Dim bd As New acceso_a_datos
    Dim estado As accion = accion.insertar

    Enum _resultado
        exitoso
        erroneo
    End Enum
    Enum accion
        insertar
        modificar
    End Enum
    Enum busqueda
        si_encontro
        no_encontro
    End Enum
    Private Sub frm_Factura_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        deshabilitar()
        Me.txt_fechaEmision.Enabled = False
        cmd_catalogo.Enabled = False
        txt_nrodocumento.Enabled = False
        cmb_tipodocumento.Enabled = False
        cmd_nuevo.Enabled = True
        cmd_buscarCliente.Enabled = False
        cmd_cancelar.Enabled = False
        grid_detalle.Enabled = False
        Me.txt_fechaEmision.Text = Today.Date()

        txt_cantidad.Enabled = False
        bd._tabla = "TipoDocumento"
        Dim tablaDoc As DataTable = bd.leo_tabla
        cargarCombo(cmb_tipodocumento, tablaDoc, "Id_TipoDocumento", "Nombre")
        cmb_tipodocumento.SelectedIndex = -1
        bd._tabla = "Medio_Pago"
        Dim tablaMedioPago As DataTable = bd.leo_tabla
        cargarCombo(cmb_medio_pago, tablaMedioPago, "Id_medio_pago", "Medio_pago")
        Me.cmb_medio_pago.SelectedIndex = -1

    End Sub

    Private Sub cargarCombo(ByRef combo As ComboBox, ByRef datos As Data.DataTable, ByRef Id As String, ByVal nombre As String)

        combo.DataSource = datos
        combo.ValueMember = Id
        combo.DisplayMember = nombre
    End Sub

    Private Sub buscar(ByVal dato As String)
        Dim tabla As New Data.DataTable
        bd._tabla = "Cliente"
        Dim sql As String

        sql = " select t.Nombre , c.Nro_Doc , c.Apellido , c.Nombres "
        sql &= "from Cliente c Inner join TipoDocumento t on c.Tipo_Doc = t.Id_TipoDocumento where c.Nro_Doc = '" & dato & "%'"
        tabla = bd._consulta(sql)



    End Sub

    Private Sub cmd_buscarCliente_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_buscarCliente.Click
        
        If validar_busqueda() = 0 Then
            Dim tabla As New Data.DataTable
            bd._tabla = "Cliente"
            Dim sql As String
            sql = " select  c.Apellido , c.Nombres "
            sql &= "from Cliente c Inner join TipoDocumento t on c.Tipo_Doc = t.Id_TipoDocumento where c.Nro_Doc = " & txt_nrodocumento.Text & " and c.Tipo_doc=" & cmb_tipodocumento.SelectedValue
            tabla = bd._consulta(sql)

            If tabla.Rows.Count > 0 Then
                txt_apellido.Text = tabla.Rows(0)("Apellido")
                txt_nombre.Text = tabla.Rows(0)("Nombres")
                habilitar()
                cmd_agregar.Enabled = False
            Else
                MsgBox("No existe un cliente con ese número de documento ", MsgBoxStyle.Critical, "Corregir")
                txt_nrodocumento.Text = ""
                cmb_tipodocumento.SelectedValue = -1
                cmb_tipodocumento.Focus()
                txt_apellido.Text = ""
                txt_nombre.Text = ""
                Exit Sub

            End If
        Else
            If validar_busqueda() = 1 Then
                MsgBox("Debe seleccionar al menos un tipo de documento", MsgBoxStyle.Critical, "Corregir")
                cmb_tipodocumento.Focus()
                Exit Sub
            Else
                MsgBox("Debe ingresar un número de documento para la búsqueda", MsgBoxStyle.Critical, "Corregir")
                txt_nrodocumento.Focus()
                Exit Sub
            End If

        End If
        cmd_catalogo.Enabled = True
        

    End Sub

    Private Function validar_busqueda() As Integer
        If (cmb_tipodocumento.SelectedIndex <> -1 And txt_nrodocumento.Text.Trim <> "") Then
            Return 0
        Else
            If cmb_tipodocumento.SelectedIndex = -1 Then
                Return 1
            Else
                Return 2
            End If
        End If
    End Function

    Private Sub actualizarStock()
        Dim consulta As String
        For c = 0 To Me.grid_detalle.RowCount() - 1
            consulta = "update Productos set Cant_Stock= Cant_Stock -" & Me.grid_detalle.Rows(c).Cells("Cantidad").Value
            consulta &= " where Productos.Id_Producto = " & Me.grid_detalle.Rows(c).Cells("Id").Value
            bd._consulta(consulta)
        Next
    End Sub
    Private Sub cmb_buscarProducto_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmb_buscarProducto.Click
        If Me.txt_codigoProducto.Text = "" Then
            MsgBox("El Código del articulo esta vacio", MsgBoxStyle.Critical, "Importante")
            txt_codigoProducto.Focus()
            Exit Sub
        End If

        Dim tabla As New Data.DataTable
        bd._tabla = "Productos"
        tabla = Me.bd.leo_tabla("Id_Producto = " & txt_codigoProducto.Text)

        If tabla.Rows.Count > 0 Then
            txt_nombreProducto.Text = tabla.Rows(0)("Nombre")
            txt_precio.Text = tabla.Rows(0)("Precio")
            txt_cantidad.Enabled = True
        Else
            MsgBox("No existe un producto con ese Codigo ", MsgBoxStyle.Critical, "Corregir")
            txt_codigoProducto.Focus()
            txt_nombreProducto.Text = ""
            txt_precio.Text = ""
            txt_cantidad.Text = ""
            txt_cantidad.Enabled = False
            txt_totalProducto.Text = ""
            Exit Sub

        End If
    End Sub

    Private Sub txt_cantidad_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txt_cantidad.TextChanged
        If IsNumeric(txt_cantidad.Text) Then
            txt_totalProducto.Text = txt_precio.Text * txt_cantidad.Text
        Else
            cmd_agregar.Enabled = False
        End If
        If IsNumeric(txt_cantidad.Text) Then
            If txt_cantidad.Text > 0 Then
                cmd_agregar.Enabled = True
            Else
                cmd_agregar.Enabled = False
            End If


        End If

    End Sub

    Private Function busca_duplicado() As Integer
        Dim c As Integer
        Dim encontro As Integer = -1

        For c = 0 To Me.grid_detalle.RowCount - 1
            If Me.grid_detalle.Rows(c).Cells(1).Value.ToString.Trim = Me.txt_codigoProducto.Text.Trim Then
                encontro = c
                Exit For
            End If
        Next
        Return encontro
    End Function
    Private Sub cmd_agregar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_agregar.Click
        Dim fila As Integer = Me.busca_duplicado()
        Dim stock As Integer = Me.calcularStock

        If (stock - txt_cantidad.Text) < 0 Then
            MsgBox("No hay disponibilidad de stock para la cantidad ingresada ", MsgBoxStyle.Critical, "Corregir")
            txt_cantidad.Focus()
            Exit Sub
        End If
        If fila > -1 Then
            Me.grid_detalle.Rows(fila).Cells(0).Value = Convert.ToDouble(Me.grid_detalle.Rows(fila).Cells(0).Value) + Convert.ToDouble(Me._txt_cantidad.Text)
            Me.grid_detalle.Rows(fila).Cells(4).Value = Convert.ToDouble(Me.grid_detalle.Rows(fila).Cells(0).Value) * Convert.ToDouble(Me._txt_precio.Text)
            txt_cantidad.Enabled = False
        Else
            Me.grid_detalle.Rows.Add(Convert.ToDouble(Me.txt_cantidad.Text), Convert.ToDouble(Me.txt_codigoProducto.Text) _
                                 , Me.txt_nombreProducto.Text, Convert.ToDouble(Me.txt_precio.Text), Convert.ToDouble(Me.txt_totalProducto.Text))
            txt_cantidad.Enabled = False
        End If
        Me.txt_totalfactura.Text = Me._calcular_total()

        Me.txt_precio.Text = ""
        Me.txt_codigoProducto.Text = ""
        Me.txt_cantidad.Text = ""
        Me.txt_nombreProducto.Text = ""
        Me.txt_totalProducto.Text = ""
        Me.txt_codigoProducto.Focus()


    End Sub



   


    Public Function _calcular_total() As Double
        Dim c As Integer
        Dim calculo As Double
        calculo = 0
        For c = 0 To Me.grid_detalle.RowCount - 1
            calculo = calculo + Convert.ToDouble(Me.grid_detalle.Rows(c).Cells(4).Value)
        Next
        Return calculo
    End Function

    Private Sub graba_factura()
        Dim txt As String
        Dim tipoFactura As String = ""
        If opt_a.Checked = True Then
            tipoFactura = "1"
        End If
        If opt_b.Checked = True Then
            tipoFactura = "2"
        End If
        If opt_c.Checked = True Then
            tipoFactura = "3"
        End If


        If estado = accion.insertar Then
            txt = "INSERT INTO Factura (Tipo_Doc, Nro_Doc, Tipo_Fact, Fecha_Emision, Medio_De_Pago, Total )"
            txt &= " VALUES ('" & Me.cmb_tipodocumento.SelectedValue & "'," & Me.txt_nrodocumento.Text & ",'" & tipoFactura & "', '" & Me.txt_fechaEmision.Text & "', '" & _
                 Me.cmb_medio_pago.SelectedValue & "', '" & Me.txt_totalfactura.Text & "' )"

            Me.bd._tabla = "Factura"
            Me.bd._consulta(txt)
        Else
            Dim modificacion As String
            modificacion = "UPDATE Factura SET Medio_Pago = '" & Me.cmb_medio_pago.SelectedValue & "', Total = " & Me.txt_totalfactura.Text & " WHERE " & txt_nrodocumento.Text & " = Factura.Nro_Doc and " & cmb_tipodocumento.SelectedValue & "= Factura.Tipo_Doc and " & tipoFactura & "= Factura.Tipo_Fact "

            Me.bd._tabla = "Factura"
            bd._consulta(modificacion)



        End If
    End Sub
    Private Function tablaFactura() As Data.DataTable
        Dim tabla As New Data.DataTable
        Dim sql As String
        bd._tabla = "Factura"
        sql = "SELECT Nro_Fact"
        sql &= " FROM Factura"
        sql &= " WHERE Tipo_Doc= " & Me.cmb_tipodocumento.SelectedValue & " AND Nro_Doc = " & Me.txt_nrodocumento.Text & " AND"
        sql &= " Fecha_Emision = '" & Me.txt_fechaEmision.Text & "' AND Total = " & Me.txt_totalfactura.Text
        tabla = bd._consulta(sql)

        Return tabla
    End Function
     Private Sub grabar_detalle()
        Dim c As Integer
        Dim txt_insert As String = ""
        Dim consulta As String = ""
        Dim tipoFactura As String = ""
        If opt_a.Checked = True Then
            tipoFactura = "1"
        End If
        If opt_b.Checked = True Then
            tipoFactura = "2"
        End If
        If opt_c.Checked = True Then
            tipoFactura = "3"
        End If

        Me.bd._tabla = "DetalleFactura"



        For c = 0 To Me.grid_detalle.RowCount() - 1
            If buscar_detalle(Me.grid_detalle.Rows(c).Cells("Id").Value) = busqueda.no_encontro Then
                consulta = "INSERT INTO DetalleFactura (Tipo_Doc, Nro_Doc, Tipo_Fact, Nro_Fact, Id_Producto, Cantidad, Precio)"
                consulta &= " VALUES(" & Me.cmb_tipodocumento.SelectedValue
                consulta &= ", " & Me.txt_nrodocumento.Text
                consulta &= ", " & tipoFactura
                consulta &= ", " & tablaFactura.Rows(0)("Nro_Fact")
                consulta &= ", " & Me.grid_detalle.Rows(c).Cells("Id").Value
                consulta &= ", " & Me.grid_detalle.Rows(c).Cells("Cantidad").Value
                consulta &= ", " & Me.grid_detalle.Rows(c).Cells("Precio").Value
                consulta &= ")"

                bd._consulta(consulta)
            Else
                'txt_set = "cantidad=" & Me.grilla1.Rows(c).Cells("cantidad").Value.ToString.Replace(",", ".")
                'txt_set &= ", precio=" & Me.grilla1.Rows(c).Cells("precio").Value.ToString.Replace(",", ".")

                'txt_res = "nro_sucursal= " & Me._txt_sucursal.Text.Trim
                'txt_res &= ",nro_pedido= " & Me._txt_pedido.Text.Trim
                'txt_res &= ",id_articulo= " & Me.grilla1.Rows(c).Cells("id").Value

                'Me._acceso._modificar(txt_set, txt_res)
                'txt_set = ""
                'txt_res = ""
            End If
        Next
    End Sub

    Private Function buscar_detalle(ByVal producto As String) As busqueda
        Dim tabla As New Data.DataTable
        Dim sql As String

        sql = "SELECT * FROM DetalleFactura"
        sql &= " WHERE Nro_Fact = " & tablaFactura.Rows(0)("Nro_Fact")
        sql &= " AND Id_producto=" & producto

        tabla = Me.bd._consulta(sql)

        Select Case tabla.Rows.Count()
            Case 0
                Return busqueda.no_encontro
            Case 1
                Return busqueda.si_encontro
        End Select

        Return True
    End Function

    Private Function validar_factura() As _resultado
        If Me.txt_nombre.Text = "" Then
            MsgBox("Falta el cliente", MsgBoxStyle.Critical, "Importante")
            Me.txt_nrodocumento.Focus()
            Return _resultado.erroneo
        End If
        If Me.cmb_medio_pago.SelectedIndex = -1 Then
            MsgBox("Falta el medio de pago", MsgBoxStyle.Critical, "Importante")
            Me.cmb_medio_pago.Focus()
            Return _resultado.erroneo
        End If

        Return _resultado.exitoso

    End Function

    Private Sub frm_Factura_FormClosing(ByVal sender As System.Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles MyBase.FormClosing
        If MsgBox("¿Esta seguro que desea salir?", MsgBoxStyle.Question + MsgBoxStyle.OkCancel, "Importante") = MsgBoxResult.Ok Then

            Me.limpiar(Me.Controls)
            txt_codigoProducto.Text = ""
            txt_nombreProducto.Text = ""
            txt_cantidad.Text = ""
            txt_precio.Text = ""
            txt_totalProducto.Text = ""
            e.Cancel = False
        Else
            e.Cancel = True
        End If
    End Sub

    Private Sub limpiar(ByVal de_donde As Object)

        Dim gri As DataGridView

        For Each obj As System.Windows.Forms.Control In de_donde
            'MsgBox(obj.Name)
            Me.grid_detalle.Rows.Clear()
            Select Case obj.GetType().ToString
                Case "System.Windows.Forms.TextBox"
                    obj.Text = ""
                Case "System.Windows.Forms.MaskedTextBox"
                    obj.Text = ""
                Case "System.Windows.Forms.DataGridView"
                    gri = obj
                    gri.Rows.Clear()
                Case "System.Windows.Forms.GroupBox"
                    Me.limpiar(obj.Controls)
            End Select
        Next obj
    End Sub

    Private Sub cmd_nuevo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_nuevo.Click
        Me.limpiar(Me.Controls)

        txt_nrodocumento.Enabled = True
        cmb_tipodocumento.Enabled = True
        
        cmd_buscarCliente.Enabled = True
        cmd_nuevo.Enabled = False
        grid_detalle.Enabled = True
        cmd_cancelar.Enabled = True
        txt_codigoProducto.Text = ""
        txt_nombreProducto.Text = ""
        txt_cantidad.Text = ""
        txt_precio.Text = ""
        txt_totalProducto.Text = ""
        cmb_tipodocumento.SelectedValue = -1
        Me.cmb_medio_pago.SelectedIndex = -1
        Me.txt_fechaEmision.Text = Today.Date()
        Me.cmb_tipodocumento.Focus()
        Me.estado = accion.insertar
    End Sub

    Private Sub cmd_salir_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_salir.Click

        Me.Close()
    End Sub

    Private Sub grid_detalle_CellDoubleClick(ByVal sender As Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles grid_detalle.CellDoubleClick
        If MsgBox("Está seguno que desea borrar esta fila de la grilla", MsgBoxStyle.Question + MsgBoxStyle.YesNo, "Importante") = MsgBoxResult.Yes Then
            Me.grid_detalle.Rows.RemoveAt(Me.grid_detalle.CurrentRow.Index)
            Me.txt_totalfactura.Text = Me._calcular_total
        End If
    End Sub

    Private Sub cmd_guardar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_guardar.Click
        If validar_factura() = _resultado.exitoso Then
            If Me.grid_detalle.Rows.Count = 0 Then
                MsgBox("Falta el detalle del pedido")
                Exit Sub
            End If
            Me.bd._iniciar_conexion_con_transaccion()
            Me.graba_factura()
            Me.grabar_detalle()
            Me.actualizarStock()

            Dim estado As Object
            estado = Me.bd._finalizar_conexio_con_transaccion()

            If estado.ToString = "_ok" Then
                MsgBox("Se grabó exitosamente", MsgBoxStyle.Information, "Importante")
                limpiar(Controls)
                deshabilitar()
                Me.txt_nrodocumento.Enabled = False
                Me.cmb_tipodocumento.Enabled = False
                Me.cmd_nuevo.Enabled = True
                Me.cmb_tipodocumento.SelectedIndex = -1


            Else
                MsgBox("Se produjo error en la grabación", MsgBoxStyle.Information, "Importante")
            End If

        End If
    End Sub

    Private Sub cmd_buscarFacturas_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_buscarFacturas.Click
        Me.limpiar(Me.Controls)
        deshabilitar()
        Me.estado = accion.modificar
        frm_buscarPedido.ShowDialog()
    End Sub

    'Private Sub recuperar_datos()
    '    Dim sql As String
    '    Dim tabla As New Data.DataTable

    '    sql = "SELECT Factura.* " & _
    '        " FROM(Factura) " & _
    '        " WHERE (Tipo_Doc = " & Trim(Me._txt_pedido.Text) & ")" & _
    '        " AND (nro_sucursal = " & Trim(Me._txt_sucursal.Text) & ")"

    '    tabla = Me._acceso._consulta(sql)

    '    Me._txt_fecha.Text = tabla.Rows(0)("fecha")
    '    Me._ObjEsp_Cliente._txt_id.Text = tabla.Rows(0)("id_cliente")

    '    Me._txt_sub_total.Text = tabla.Rows(0)("sub_total")
    '    Me._txt_total.Text = tabla.Rows(0)("total")
    '    Me._txt_decuento.Text = IIf(tabla.Rows(0)("descuentos").GetType().ToString = "System.DBNull", "0", tabla.Rows(0)("descuentos"))

    '    sql = "SELECT t_detalles_pedidos.cantidad, t_articulos.id_articulo, t_articulos.n_articulo, t_detalles_pedidos.precio" & _
    '          " FROM (t_detalles_pedidos INNER JOIN " & _
    '          " t_articulos ON t_detalles_pedidos.id_articulo = t_articulos.id_articulo) " & _
    '          " WHERE (nro_pedido = " & Trim(Me._txt_pedido.Text) & ")" & _
    '          " AND (nro_sucursal = " & Trim(Me._txt_sucursal.Text) & ")"


    '    tabla = Me._acceso._consulta(sql)

    '    Dim c As Integer

    '    For c = 0 To tabla.Rows.Count() - 1
    '        Me.grilla1.Rows.Add(tabla.Rows(c)("cantidad"), tabla.Rows(c)("id_articulo") _
    '                            , tabla.Rows(c)("n_articulo"), tabla.Rows(c)("precio") _
    '                            , tabla.Rows(c)("cantidad") * tabla.Rows(c)("precio"))
    '    Next


    'End Sub

    Private Sub cmd_cancelar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_cancelar.Click

        txt_nrodocumento.Enabled = False
        cmb_tipodocumento.Enabled = False
        deshabilitar()

        cmd_nuevo.Enabled = True
        cmd_cancelar.Enabled = False
        Me.limpiar(Me.Controls)
        txt_codigoProducto.Text = ""
        txt_nombreProducto.Text = ""
        txt_cantidad.Text = ""
        txt_precio.Text = ""
        txt_totalProducto.Text = ""
        cmb_tipodocumento.SelectedValue = -1
        Me.cmb_medio_pago.SelectedIndex = -1
    End Sub
    Private Sub habilitar()
        txt_codigoProducto.Enabled = True
        cmb_buscarProducto.Enabled = True

        cmb_medio_pago.Enabled = True
        cmd_agregar.Enabled = True
        cmd_guardar.Enabled = True
        opt_a.Enabled = True
        opt_b.Enabled = True
        opt_c.Enabled = True
        opt_a.Focus()
    End Sub

    Private Sub deshabilitar()
        txt_codigoProducto.Enabled = False
        cmb_buscarProducto.Enabled = False
        txt_cantidad.Enabled = False
        cmb_medio_pago.Enabled = False
        cmd_agregar.Enabled = False
        cmd_guardar.Enabled = False
        opt_a.Enabled = False
        opt_b.Enabled = False
        opt_c.Enabled = False
    End Sub
    Private Function obtenerFecha()
        Dim fechacreacion As Date
        fechacreacion = txt_fechaEmision.Text
        Dim fechacreacionstring = Format(fechacreacion, "MM/dd/yyyy")
        Return fechacreacionstring
    End Function

    Private Function calcularStock() As Integer
        Dim fila As Integer = Me.busca_duplicado()
        Dim sql As String
        Dim tabla As New DataTable
        Dim stock As Integer = 0
        sql = " select Productos.Cant_Stock from Productos where Productos.Id_Producto =" & txt_codigoProducto.Text
        bd._tabla = "Productos"
        tabla = bd._consulta(sql)
        stock = tabla.Rows(0)("Cant_Stock")
        If fila > -1 Then
            stock = stock - Me.grid_detalle.Rows(fila).Cells(0).Value
        End If
        Return stock
    End Function

    Private Sub Varios_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles txt_nrodocumento.KeyPress, txt_cantidad.KeyPress
        'Tomo la letra ingresada por teclado
        'La transformo a ascii para analizarla        
        Select Case Asc(e.KeyChar)
            'Hago excepciones para teclas especiales como Tab, Borrar, Flechas, etc.
            Case 4, 24, 19, 127, 13, 9, 15, 14, 8
                Exit Sub
        End Select
        'Si es un numero no pasa nada
        'Si no es un numero informo y no dejo que se ingrese
        If IsNumeric(e.KeyChar) = False Then
            MsgBox("Este caracter no es un número (" + e.KeyChar + ")", vbCritical, "Importante")
            e.KeyChar = ""
        End If
    End Sub

    Private Sub cmd_catalogo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_catalogo.Click
        Listado_Productos_Grafico.Show()
    End Sub
End Class