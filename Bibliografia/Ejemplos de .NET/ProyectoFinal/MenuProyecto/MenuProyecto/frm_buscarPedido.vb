Public Class frm_buscarPedido

    Private Sub frm_buscarPedido_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Me.buscar_datos()
    End Sub

    Private Sub cmd_salir_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_salir.Click
        Me.Close()
    End Sub

    Private Sub _grid1_CellDoubleClick(ByVal sender As Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles _grid1.CellDoubleClick
        Dim bd As New acceso_a_datos
        Dim sql As String
        Dim sql2 As String
        Dim tablaL As New Data.DataTable
        Dim tabla2 As New Data.DataTable
        Dim tipoDoc As Integer
        If Me._grid1.CurrentRow.Cells(0).Value = "DNI" Then
            tipoDoc = 1
        End If
        If Me._grid1.CurrentRow.Cells(0).Value = "PASAPORTE" Then
            tipoDoc = 2
        End If
        If Me._grid1.CurrentRow.Cells(0).Value = "LE" Then
            tipoDoc = 3
        End If
        If Me._grid1.CurrentRow.Cells(0).Value = "LC" Then
            tipoDoc = 4
        End If
        If Me._grid1.CurrentRow.Cells(0).Value = "CEDULA" Then
            tipoDoc = 5
        End If
        If Me._grid1.CurrentRow.Cells(0).Value = "S/D" Then
            tipoDoc = 6
        End If

        sql = "SELECT Factura.* , Cliente.Nombres, Cliente.Apellido " & _
              " FROM Factura INNER JOIN Cliente on Factura.Tipo_Doc = Cliente.Tipo_Doc and Factura.Nro_Doc = Cliente.Nro_Doc  where Factura.Tipo_Doc = " & tipoDoc & "and Factura.Nro_Doc =" & Me._grid1.CurrentRow.Cells(1).Value & " and Factura.Tipo_Fact =" & Me._grid1.CurrentRow.Cells(2).Value & " and Factura.Nro_Fact= " & Me._grid1.CurrentRow.Cells(3).Value
        tablaL = bd._consulta(sql)
        frm_Factura.cmb_tipodocumento.SelectedValue = tablaL.Rows(0)("Tipo_Doc")
        frm_Factura.txt_fechaEmision.Text = tablaL.Rows(0)("Fecha_Emision")
        If tablaL.Rows(0)("Tipo_Fact") = 1 Then
            frm_Factura.opt_a.Checked = True
        End If
        If tablaL.Rows(0)("Tipo_Fact") = 2 Then
            frm_Factura.opt_b.Checked = True
        End If
        If tablaL.Rows(0)("Tipo_Fact") = 3 Then
            frm_Factura.opt_c.Checked = True
        End If
        frm_Factura.txt_nrodocumento.Text = tablaL.Rows(0)("Nro_Doc")
        frm_Factura.txt_nombre.Text = tablaL.Rows(0)("Nombres")
        frm_Factura.txt_apellido.Text = tablaL.Rows(0)("Apellido")
        frm_Factura.cmb_medio_pago.SelectedValue = tablaL.Rows(0)("Medio_De_Pago")


        sql2 = " SELECT        DetalleFactura.Id_Producto, DetalleFactura.Precio, DetalleFactura.Cantidad, Productos.Nombre"
        sql2 &= "            FROM            DetalleFactura INNER JOIN"
        sql2 &= "      Productos ON DetalleFactura.Id_Producto = Productos.Id_Producto where DetalleFactura.Tipo_Doc = " & frm_Factura.cmb_tipodocumento.SelectedValue & " and DetalleFactura.Nro_Doc = " & frm_Factura.txt_nrodocumento.Text & " and DetalleFactura.Tipo_Fact = " & tablaL.Rows(0)("Tipo_Fact") & " and  DetalleFactura.Nro_Fact =" & tablaL.Rows(0)("Nro_Fact")
        tabla2 = bd._consulta(sql2)
        Dim c As Integer

        For c = 0 To tabla2.Rows.Count() - 1
            frm_Factura.grid_detalle.Rows.Add(tabla2.Rows(c)("Cantidad"), tabla2.Rows(c)("Id_Producto") _
                                , tabla2.Rows(c)("Nombre"), tabla2.Rows(c)("Precio") _
                                , tabla2.Rows(c)("Cantidad") * tabla2.Rows(c)("Precio"))
        Next
        frm_Factura.txt_totalfactura.Text = frm_Factura._calcular_total()
        frm_Factura.txt_cantidad.Enabled = False
        frm_Factura.txt_nrodocumento.Enabled = False
        frm_Factura.cmb_tipodocumento.Enabled = False
        frm_Factura.txt_codigoProducto.Enabled = False
        frm_Factura.cmb_buscarProducto.Enabled = False
        frm_Factura.cmd_buscarCliente.Enabled = False
        frm_Factura.cmb_medio_pago.Enabled = False
        frm_Factura.cmd_agregar.Enabled = False
        frm_Factura.cmd_guardar.Enabled = False
        frm_Factura.cmd_nuevo.Enabled = False
        frm_Factura.opt_a.Enabled = False
        frm_Factura.opt_b.Enabled = False
        frm_Factura.cmd_cancelar.Enabled = True
        frm_Factura.opt_c.Enabled = False
        frm_Factura.grid_detalle.Enabled = False






        Me.Close()

    End Sub

    Private Sub buscar_datos()
        Dim bd As New acceso_a_datos

        Dim sql As String
        Dim tablaL As New Data.DataTable

        sql = "SELECT TipoDocumento.Nombre, Factura.Nro_Doc, Factura.Tipo_Fact, Factura.Nro_Fact, Factura.Fecha_Emision, Factura.Total " & _
              " FROM Factura INNER JOIN TipoDocumento on Factura.Tipo_Doc = TipoDocumento.Id_TipoDocumento "

        tablaL = bd._consulta(sql)

        Dim c As Integer

        Me._grid1.Rows.Clear()

        For c = 0 To tablaL.Rows.Count() - 1
            Me._grid1.Rows.Add(tablaL.Rows(c)("Nombre"), tablaL.Rows(c)("Nro_Doc"), tablaL.Rows(c)("Tipo_Fact") _
            , tablaL.Rows(c)("Nro_Fact"), tablaL.Rows(c)("Fecha_Emision"), tablaL.Rows(c)("Total"))
        Next

    End Sub


 
End Class
