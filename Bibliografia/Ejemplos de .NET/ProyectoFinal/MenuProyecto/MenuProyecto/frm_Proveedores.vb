Public Class frm_proveedores

    Dim bd As New acceso_a_datos
    Enum estado
        insertar
        eliminar
        modificar
    End Enum
    Enum termino
        aprobado
        rechazado
    End Enum

    Dim accion As estado = estado.insertar

    Private Sub frm_proveedores_FormClosing(ByVal sender As Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles Me.FormClosing
        If MessageBox.Show("¿Esta seguro que desea salir?", "Importante", _
              MessageBoxButtons.YesNo, MessageBoxIcon.Question) _
          = Windows.Forms.DialogResult.Yes Then
            e.Cancel = False
        Else
            e.Cancel = True
        End If
    End Sub

    Private Sub Proveedores_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Me.deshabilitar()
        cmd_grabar.Enabled = False
        cmd_eliminar.Enabled = False
        cmd_cancelar.Enabled = False
        cmd_modificar.Enabled = False
        cmd_agregarbarrio.Enabled = False
        cmd_agregarlocalidad.Enabled = False


        bd._tabla = "Provincia"
        Dim tablaProvincia As DataTable = bd.leo_tabla

        cargar_combo(cmb_Provincia, tablaProvincia, "Id_Provincia", "Nombre")
        cmb_Provincia.SelectedIndex = -1

        cargarGrilla()
    End Sub

    Private Sub cargarGrilla()
        Dim sql As String
        sql = "SELECT Id_Proveedores, Razon_Social, Telefono_Contacto, Nombre_Contacto, Apellido_Contacto "
        sql &= "from Proveedores"
        Me.grid_proveedor.Rows.Clear()
        Dim tabla As DataTable = bd._consulta(sql)

        For c = 0 To tabla.Rows.Count() - 1
            Me.grid_proveedor.Rows.Add(tabla.Rows(c)("Id_Proveedores"), tabla.Rows(c)("Razon_Social"), tabla.Rows(c)("Telefono_Contacto"), tabla.Rows(c)("Nombre_Contacto"), tabla.Rows(c)("Apellido_Contacto"))
        Next

    End Sub

    'CARGA DE COMBOS

    Private Sub cargar_combo(ByRef combo As ComboBox, ByRef datos As Data.DataTable, ByRef Id As String, ByVal nombre As String)

        combo.DataSource = datos
        combo.ValueMember = Id
        combo.DisplayMember = nombre

    End Sub

    Private Sub cmb_provincia_SelectionChangeCommitted(ByVal sender As Object, ByVal e As System.EventArgs) Handles cmb_Provincia.SelectionChangeCommitted
        bd._tabla = "Localidad"
        Dim tablaLocalidad As DataTable = bd.leo_tabla("Id_Provincia = " & cmb_provincia.SelectedValue)
        cargar_combo(cmb_localidad, tablaLocalidad, "Id_Localidad", "Nombre")
        cmb_localidad.Enabled = True
        cmb_localidad.SelectedIndex = -1
    End Sub

    Private Sub cmb_localidad_SelectionChangeCommitted(ByVal sender As Object, ByVal e As System.EventArgs) Handles cmb_localidad.SelectionChangeCommitted
        bd._tabla = "Barrio"
        Dim tablaBarrio As DataTable = bd.leo_tabla("Id_Localidad = " & cmb_localidad.SelectedValue)
        cargar_combo(cmb_barrio, tablaBarrio, "Id_Barrio", "Nombre")
        cmb_barrio.Enabled = True
        cmb_barrio.SelectedIndex = -1

    End Sub

    'BOTONES

    Private Sub cmd_nuevo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_nuevo.Click

        habilitar()
        limpiar()
        txt_razon_social.Focus()
        cmd_grabar.Enabled = True
        cmd_cancelar.Enabled = True
        cmd_eliminar.Enabled = False
        accion = estado.insertar
        cmd_agregarbarrio.Enabled = True
        cmd_agregarlocalidad.Enabled = True


    End Sub

    Private Sub cmd_grabar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_grabar.Click

        If validar() = True Then

            If accion = estado.insertar Then
                If validar_existencia() = termino.aprobado Then
                    Dim sql As String
                    sql = "INSERT INTO Proveedores(Razon_Social, Telefono_Contacto, Nombre_Contacto, Apellido_Contacto, Calle, Nro, Barrio)"
                    sql &= "VALUES('" & Me.txt_razon_social.Text & "'," & Me.txt_telefono.Text & ",'" & Me.txt_nombre.Text & "', '" & Me.txt_apellido.Text & "', '" & _
                         Me.txt_calle.Text & "', " & Me.txt_numero.Text & ",'" & Me.cmb_barrio.SelectedValue & "')"
                    bd._consulta(sql)
                    cargarGrilla()
                    MessageBox.Show("Se grabó exitosamente", "Importante", MessageBoxButtons.OK, MessageBoxIcon.Information)
                    cmd_grabar.Enabled = False
                Else
                    MsgBox("Ya existe un proveedor con esa razón social, ingrese otro", MsgBoxStyle.Information, "Importante")
                    txt_razon_social.Text = ""
                    txt_razon_social.Focus()
                    Return

                End If

                If accion = estado.modificar Then
                    Dim id As Integer = Me.grid_proveedor.CurrentRow.Cells("Id_Proveedor").Value
                    Dim modificacion As String
                    modificacion = ("UPDATE Proveedores SET Razon_Social = '" & Me.txt_razon_social.Text & "', Telefono_Contacto = " & Me.txt_telefono.Text & ",  Nombre_Contacto = '" & Me.txt_nombre.Text & _
                                    "', Apellido_Contacto = '" & Me.txt_apellido.Text & "', Calle = '" & Me.txt_calle.Text & "', Nro = " & Me.txt_numero.Text & _
                                    ", Barrio = '" & Me.cmb_barrio.SelectedValue & "'" & " WHERE " & id & " = Proveedores.Id_Proveedores")
                    bd._consulta(modificacion)

                    cargarGrilla()
                    MessageBox.Show("Se modificó exitosamente", "Importante", MessageBoxButtons.OK, MessageBoxIcon.Information)
                End If
           
            End If
            cmd_cancelar.Enabled = False
            cmd_modificar.Enabled = False
            cmd_grabar.Enabled = False
            limpiar()
            deshabilitar()
        End If
    End Sub

    Private Function validar_existencia() As termino
        Dim tabla As New Data.DataTable
        Dim sql As String

        sql = "SELECT * FROM Proveedores"
        sql &= " WHERE Razon_Social =  '" & Me.txt_razon_social.Text & "'"
        tabla = bd._consulta(sql)

        If tabla.Rows.Count = 1 Then
            Return termino.rechazado
        Else
            Return termino.aprobado

        End If

    End Function

    Private Sub cmd_eliminar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_eliminar.Click
        Dim id As Integer = Me.grid_proveedor.CurrentRow.Cells("Id_Proveedor").Value
        If MessageBox.Show("¿Esta seguro que desea eliminar la fila seleccionada?", "Atención", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button1) = Windows.Forms.DialogResult.OK Then
            bd._consulta(" DELETE FROM Proveedores where " & id & " = Proveedores.Id_Proveedores")

            cargarGrilla()
            deshabilitar()
            cmd_nuevo.Enabled = True
            cmd_limpiar.Enabled = False
            cmd_modificar.Enabled = False



        End If
    End Sub

    Private Sub cmd_salir_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_salir.Click
        Me.Close()

    End Sub

    'Metodo que valida ingreso numerico

    Private Sub numeros_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles txt_numero.KeyPress, txt_telefono.KeyPress
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

    'Metodo que valida ingreso de caracteres

    Private Sub Letra_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles txt_razon_social.KeyPress, txt_nombre.KeyPress, txt_calle.KeyPress, txt_apellido.KeyPress
        'Tomo la letra ingresada por teclado
        'La transformo a ascii para analizarla        
        Select Case Asc(e.KeyChar)
            'Hago excepciones para teclas especiales como Tab, Borrar, Flechas, etc.
            Case 4, 24, 19, 127, 13, 9, 15, 14, 8
                Exit Sub
        End Select
        ' si es una letra no pasa nada
        'Si es un numero  informo y no dejo que se ingrese
        If IsNumeric(e.KeyChar) = True Then
            MsgBox("Este caracter no es una letra (" + e.KeyChar + ")", vbCritical, "Importante")
            e.KeyChar = ""
        End If
    End Sub

    Private Function validar() As Boolean
        If txt_razon_social.Text = "" Then
            MsgBox("Falta ingresar la razón social", MsgBoxStyle.Critical, "Importante")
            Me.txt_razon_social.Focus()
            Return False
            Exit Function
        End If

        If txt_telefono.Text = "" Then
            MsgBox("Falta ingresar el teléfono del contacto", MsgBoxStyle.Critical, "Importante")
            Me.txt_telefono.Focus()
            Return False
            Exit Function
        End If

        If txt_nombre.Text = "" Then
            MsgBox("Falta ingresar el nombre del contacto", MsgBoxStyle.Critical, "Importante")
            Me.txt_nombre.Focus()
            Return False
            Exit Function
        End If

        If txt_apellido.Text = "" Then
            MsgBox("Falta ingresar el apellido del contacto", MsgBoxStyle.Critical, "Importante")
            Me.txt_nombre.Focus()
            Return False
            Exit Function
        End If

        If txt_calle.Text = "" Then
            MsgBox("Falta ingresar la calle", MsgBoxStyle.Critical, "Importante")
            Me.txt_nombre.Focus()
            Return False
            Exit Function
        End If

        If txt_numero.Text = "" Then
            MsgBox("Falta ingresar la numeración de la calle", MsgBoxStyle.Critical, "Importante")
            Me.txt_nombre.Focus()
            Return False
            Exit Function
        End If

        If cmb_barrio.SelectedIndex = -1 Then
            MsgBox("Seleccione un barrio correcto", MsgBoxStyle.Critical, "Importante")
            Me.cmb_barrio.Focus()
            Return False
            Exit Function
        End If


        Return True
    End Function

    Private Sub deshabilitar()
        txt_razon_social.Enabled = False
        txt_telefono.Enabled = False
        txt_nombre.Enabled = False
        txt_apellido.Enabled = False
        txt_calle.Enabled = False
        txt_numero.Enabled = False
        cmb_Provincia.Enabled = False
        cmb_localidad.Enabled = False
        cmb_barrio.Enabled = False


    End Sub

    Private Sub habilitar()
        txt_razon_social.Enabled = True
        txt_telefono.Enabled = True
        txt_nombre.Enabled = True
        txt_apellido.Enabled = True
        txt_calle.Enabled = True
        txt_numero.Enabled = True
        cmb_Provincia.Enabled = True
        cmb_localidad.Enabled = True
        cmb_barrio.Enabled = True

        cmd_eliminar.Enabled = True

    End Sub

    Private Sub limpiar()

        Dim objeto As Control
        For Each objeto In Me.Controls
            If TypeOf objeto Is TextBox Then
                objeto.Text = ""
            ElseIf TypeOf objeto Is GroupBox Then
                For Each elemento In objeto.Controls
                    If TypeOf elemento Is TextBox Then
                        elemento.Text = ""
                    ElseIf TypeOf elemento Is ComboBox Then
                        elemento.SelectedIndex = -1
                    End If
                Next
            End If
        Next

        'cmb_Provincia.SelectedIndex = -1
        'cmb_localidad.SelectedIndex = -1
        'cmb_barrio.SelectedIndex = -1
    End Sub

    Private Sub cmd_cancelar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_cancelar.Click
        If MessageBox.Show("¿Esta seguro que desea cancelar?", "Atención", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button1) = Windows.Forms.DialogResult.OK Then
            limpiar()
            deshabilitar()
            cargarGrilla()

            cmd_modificar.Enabled = False
            cmd_nuevo.Enabled = True
            cmd_cancelar.Enabled = False
            cmd_grabar.Enabled = False
            cmd_eliminar.Enabled = False
        End If
    End Sub

    Private Sub cmd_modificar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_modificar.Click
        habilitar()
        cmd_grabar.Enabled = True
        cmd_cancelar.Enabled = True
        cmd_modificar.Enabled = False
        cmd_eliminar.Enabled = False
        cmd_cancelar.Enabled = True
        cmd_agregarbarrio.Enabled = True
        cmd_agregarlocalidad.Enabled = True


        accion = estado.modificar
    End Sub


    Private Sub grid_proveedor_CellDoubleClick(ByVal sender As Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles grid_proveedor.CellDoubleClick
        Dim id As Integer = Me.grid_proveedor.CurrentRow.Cells("Id_Proveedor").Value
        Dim tablaProveedores As New DataTable

        deshabilitar()
        cmd_modificar.Enabled = True
        cmd_eliminar.Enabled = True
        cmd_nuevo.Enabled = False
        cmd_grabar.Enabled = False
        cmd_agregarbarrio.Enabled = False
        cmd_agregarlocalidad.Enabled = False
        cmd_cancelar.Enabled = True


        Dim sql As String
        sql = "SELECT *"
        sql &= " FROM Proveedores Pr "
        sql &= "inner join Barrio b on Pr.Barrio = b.Id_Barrio"
        sql &= " inner join Localidad l on b.Id_Localidad = l.Id_Localidad"
        sql &= " inner join Provincia p on l.Id_Provincia = p.Id_Provincia"
        sql &= " WHERE Pr.Id_Proveedores = '" & id & "'"

        tablaProveedores = bd._consulta(sql)

        txt_nombre.Text = tablaProveedores.Rows(0)("Nombre_Contacto")
        txt_apellido.Text = tablaProveedores.Rows(0)("Apellido_Contacto")
        txt_calle.Text = tablaProveedores.Rows(0)("Calle")
        txt_numero.Text = tablaProveedores.Rows(0)("Nro")
        txt_razon_social.Text = tablaProveedores.Rows(0)("Razon_Social")
        txt_telefono.Text = tablaProveedores.Rows(0)("Telefono_Contacto")

        cmb_Provincia.SelectedValue = tablaProveedores.Rows(0)("Id_Provincia")

        bd._tabla = "Localidad"
        Dim tablaLocalidad As DataTable = bd.leo_tabla("Id_Provincia = " & tablaProveedores.Rows(0)("Id_Provincia"))
        cargar_combo(cmb_localidad, tablaLocalidad, "Id_Localidad", "Nombre")
        cmb_localidad.SelectedValue = tablaProveedores.Rows(0)("Id_Localidad")

        bd._tabla = "Barrio"
        Dim tablaBarrio As DataTable = bd.leo_tabla("Id_Localidad = " & tablaProveedores.Rows(0)("Id_Localidad"))
        cargar_combo(cmb_barrio, tablaBarrio, "Id_Barrio", "Nombre")

        cmb_barrio.SelectedValue = tablaProveedores.Rows(0)("Id_Barrio")




        accion = estado.modificar
    End Sub

    'Busqueda
    Private Sub cargar_Grilla_Busqueda(ByVal tabla As DataTable)
        Me.grid_proveedor.Rows.Clear()
       For c = 0 To tabla.Rows.Count() - 1
            Me.grid_proveedor.Rows.Add(tabla.Rows(c)("Id_Proveedores"), tabla.Rows(c)("Razon_Social"), tabla.Rows(c)("Telefono_Contacto"), _
                                       tabla.Rows(c)("Nombre_Contacto"), tabla.Rows(c)("Apellido_Contacto"))
        Next
    End Sub

    Private Sub buscar(ByVal dato As String)
        Dim tabla As New Data.DataTable
        bd._tabla = "Proveedores"
        If IsNumeric(dato) Then
            tabla = Me.bd.leo_tabla("Id_Proveedores = " & dato)
        Else
            tabla = Me.bd.leo_tabla("Razon_Social like '%" & dato & "%'")
        End If

        cargar_Grilla_Busqueda(tabla)
    End Sub

    Private Sub cmd_limpiar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_limpiar.Click
        Me.txt_buscar.Text = ""
        Me.txt_buscar.Focus()
        Me.cmd_limpiar.Enabled = False
    End Sub

    Private Sub txt_buscar_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles txt_buscar.TextChanged
        cmd_limpiar.Enabled = True
        buscar(Me.txt_buscar.Text)
    End Sub

    Private Sub cmd_agregarlocalidad_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_agregarlocalidad.Click
        frm_Localidad.Show()
    End Sub

    Private Sub cmd_agregarbarrio_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_agregarbarrio.Click
        frm_Barrio.Show()
    End Sub

    
End Class