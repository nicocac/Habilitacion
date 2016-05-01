Public Class frm_Barrio

    Dim bd As New acceso_a_datos
    Enum estado
        insertar
        eliminar
        modificar
    End Enum
    Dim pk_barrio_modif As String
    Dim barrio_modif As String
    Dim localidad_modif As String
    Dim accion As estado = estado.insertar

    Private Sub cmd_nuevo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_nuevo.Click
        Me.txt_nombre.Text = ""
        Me.cmb_localidad.Enabled = True
        Me.cmb_Provincia.Enabled = True
        Me.txt_nombre.Focus()
        Me.txt_nombre.Enabled = True
        Me.cmd_grabar.Enabled = True
        Me.cmd_modificar.Enabled = False
        Me.cmd_nuevo.Enabled = False
        Me.cmd_eliminar.Enabled = False
        cmd_cargar_localidad.Enabled = True
        cmd_limpiar_datos.Enabled = True
    End Sub

    Private Sub cargar_combo(ByRef combo As ComboBox, ByRef datos As Data.DataTable, ByVal pk As String, ByVal descripcion As String)
        combo.DataSource = datos
        combo.ValueMember = pk
        combo.DisplayMember = descripcion
    End Sub

    Private Sub cmb_provincia_SelectionChangeCommitted(ByVal sender As Object, ByVal e As System.EventArgs) Handles cmb_Provincia.SelectionChangeCommitted
        bd._tabla = "Localidad"
        Dim tablaLocalidad As DataTable = bd.leo_tabla("Id_Provincia = " & cmb_provincia.SelectedValue)
        cargar_combo(cmb_localidad, tablaLocalidad, "Id_Localidad", "Nombre")
        cmb_localidad.Enabled = True
        cmb_localidad.SelectedIndex = -1
    End Sub

    Private Sub Barrio_FormClosing(ByVal sender As Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles Me.FormClosing
        If MessageBox.Show("¿Esta seguro que desea salir?", "Importante", _
MessageBoxButtons.YesNo, MessageBoxIcon.Question) _
= Windows.Forms.DialogResult.Yes Then
            e.Cancel = False
        Else
            e.Cancel = True
        End If
    End Sub
    Private Sub Barrio_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Me.cargarGrilla()
        bd._tabla = "Provincia"
        cargar_combo(cmb_Provincia, bd.leo_tabla(), "Id_Provincia", "Nombre")

        Me.cmb_localidad.SelectedIndex = -1
        Me.cmb_Provincia.SelectedIndex = -1
        Me.cmb_localidad.Enabled = False
        Me.txt_nombre.Focus()
        Me.cmb_Provincia.Enabled = False
        Me.cmd_grabar.Enabled = False
        Me.cmd_eliminar.Enabled = False
        Me.cmd_modificar.Enabled = False
        Me.txt_nombre.Enabled = False
        Me.cmd_limpiar.Enabled = False
        cmd_cargar_localidad.Enabled = False
        Me.cmd_limpiar_datos.Enabled = False

    End Sub

    Private Sub cargarGrilla()
        Dim consulta As String = ""
        consulta = "SELECT        B.Id_Barrio, B.Nombre, L.Nombre AS Expr1"
        consulta &= " FROM            Barrio AS B INNER JOIN Localidad AS L ON B.Id_Localidad = L.Id_Localidad"
        Me.grid_barrio.Rows.Clear()
        Dim tabla As New DataTable
        tabla = bd._consulta(consulta)
        For c = 0 To tabla.Rows.Count() - 1
            Me.grid_barrio.Rows.Add(tabla.Rows(c)(0), tabla.Rows(c)(1), tabla.Rows(c)(2))
        Next
    End Sub

    Private Sub cmd_salir_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_salir.Click
        Me.Close()
    End Sub

    Private Sub cmd_grabar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_grabar.Click
        bd._tabla = "Barrio"
        Dim tablaBarrio As DataTable = bd.leo_tabla
        Dim sql As String

        If accion = estado.insertar Then
            sql = "SELECT Nombre FROM Barrio WHERE Nombre='" & Me.txt_nombre.Text & "'and Id_Localidad='" & Me.buscar_id_localidad(Me.cmb_localidad.Text) & "'"
            Dim tabla As DataTable = bd._consulta(sql)

            If tabla.Rows.Count = 0 Then
                Dim comando As String
                Dim id_buscada As Integer = buscar_id_localidad(Me.cmb_localidad.Text)
                comando = "Insert into Barrio(Nombre, Id_Localidad) values ('" & Me.txt_nombre.Text & "', '" & id_buscada & "')"
                bd._modificar(comando)
                bd._tabla = "Barrio"
                Me.cargarGrilla()
                limpiar()
                MsgBox("Se cargo exitosamente")
            Else
                MsgBox("El barrio ya fue cargado en la base de datos")
            End If

        ElseIf accion = estado.modificar Then
            Dim comando As String
            Dim id_buscada As Integer = buscar_id_localidad(Me.cmb_localidad.Text)
            comando = "UPDATE Barrio SET Nombre = '" & Me.txt_nombre.Text & "', Id_localidad = " & id_buscada & " WHERE Nombre ='" & barrio_modif & "' AND Id_Localidad = " & buscar_id_localidad(localidad_modif) & " AND Id_Barrio = " & pk_barrio_modif
            bd._modificar(comando)
            bd._tabla = "Barrio"
            Me.cargarGrilla()
            limpiar()
            MsgBox("Se modifico exitosamente")
            accion = estado.insertar
        End If

        cmd_nuevo.Enabled = True
        cmd_limpiar_datos.Enabled = False
        limpiar()
    End Sub

    Private Function buscar_id_localidad(ByVal localidad As String) As Integer
        bd._tabla = "Localidad"
        Dim tablaLocalidad As DataTable = bd.leo_tabla
        Dim id As Integer = 0
        id = buscar_id(tablaLocalidad, localidad)
        Return id
    End Function

    Private Function buscar_id(ByVal tabla As DataTable, ByVal nombre As String) As Integer
        For c = 0 To tabla.Rows.Count - 1
            If tabla.Rows(c)("Nombre") = nombre Then
                Return tabla.Rows(c)("Id_Localidad")
                Exit For
            End If
        Next
        Return -1
    End Function

    Private Sub cmd_eliminar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_eliminar.Click
        Dim sql As String
        Dim comando As String
        comando = "SELECT Nombre FROM Barrio WHERE Nombre='" & Me.txt_nombre.Text & "'and Id_Localidad='" & Me.buscar_id_localidad(Me.cmb_localidad.Text) & "'"
        Dim tabla As DataTable = bd._consulta(comando)
        If Not (tabla.Rows.Count = 0) Then
            sql = "DELETE FROM Barrio WHERE Nombre='" & Me.txt_nombre.Text & "'and Id_Localidad='" & Me.buscar_id_localidad(Me.cmb_localidad.Text) & "'"
            bd._borrar(sql)
            bd._tabla = "Barrio"
            Me.cargarGrilla()
            MsgBox("Se ha borrado exitosamente")

        Else
            MsgBox("No existe el barrio que desea borrar")
        End If

        limpiar()
        cmd_grabar.Enabled = False
        cmd_modificar.Enabled = False
        cmd_limpiar_datos.Enabled = False
        cmd_nuevo.Enabled = True


    End Sub

    Private Sub lbl_cargar_localidad_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_cargar_localidad.Click
        frm_Localidad.Show()
    End Sub

    Private Sub buscar(ByVal dato As String)
        Dim tabla As New Data.DataTable
        bd._tabla = "Barrio"
        If IsNumeric(dato) Then
            tabla = Me.bd.leo_tabla("Id_Barrio = " & dato)
        Else
            tabla = Me.bd.leo_tabla("Nombre like '%" & dato & "%'")
        End If
        Me.cargarGrillaBusqueda(tabla)
    End Sub

    Private Sub txt_descripcion_TextChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles txt_descripcion.TextChanged
        cmd_limpiar.Enabled = True
        buscar(Me.txt_descripcion.Text)
    End Sub

    Private Sub cmd_limpiar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_limpiar.Click
        Me.txt_descripcion.Text = ""
        Me.txt_descripcion.Focus()
        Me.cmd_limpiar.Enabled = False
    End Sub

    Private Sub cmd_limpiar_datos_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_limpiar_datos.Click
        limpiar()
        Me.cmd_nuevo.Enabled = True
        Me.cmb_localidad.Enabled = False
        Me.cmb_Provincia.Enabled = False
        Me.cmb_localidad.SelectedIndex = -1
        Me.cmb_Provincia.SelectedIndex = -1
        Me.cmd_limpiar_datos.Enabled = False

    End Sub

    Private Sub limpiar()
        Me.txt_nombre.Text = ""
        Me.cmb_localidad.SelectedValue = -1
        Me.cmb_Provincia.SelectedValue = -1
        Me.cmb_localidad.Text = ""
        Me.cmd_grabar.Enabled = False
        Me.cmd_eliminar.Enabled = False
        Me.cmd_modificar.Enabled = False
        Me.txt_nombre.Enabled = False
    End Sub


    Private Sub cmd_modificar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_modificar.Click
        accion = estado.modificar
        cmd_cargar_localidad.Enabled = True
        cmd_grabar.Enabled = True
    End Sub

    Private Sub grid_barrio_CellDoubleClick1(ByVal sender As Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles grid_barrio.CellDoubleClick
        Dim sql As String = ""
        Dim tabla As New Data.DataTable
        Dim id_barrio As Integer = Me.grid_barrio.CurrentRow.Cells("id_barrio").Value
        sql = "SELECT  B.Nombre, L.Id_Provincia, L.Id_Localidad"
        sql &= " FROM            Localidad L INNER JOIN Barrio B ON L.Id_Localidad = B.Id_Localidad"
        sql &= " WHERE B.Id_Barrio = " & id_barrio
        tabla = bd._consulta(sql)

        Me.txt_nombre.Text = tabla.Rows(0)("Nombre")
        cmb_Provincia.SelectedValue = tabla.Rows(0)("Id_Provincia")

        bd._tabla = "Localidad"
        Dim tablaLocalidad As DataTable = bd.leo_tabla("Id_Provincia = " & tabla.Rows(0)("Id_Provincia"))
        cargar_combo(cmb_localidad, tablaLocalidad, "Id_Localidad", "Nombre")
        cmb_localidad.SelectedValue = tabla.Rows(0)("Id_Localidad")

        cmd_nuevo.Enabled = False
        cmd_modificar.Enabled = True
        cmd_eliminar.Enabled = True
        cmd_cargar_localidad.Enabled = False
        cmd_grabar.Enabled = False
        txt_nombre.Enabled = False
        cmb_localidad.Enabled = False
        cmb_Provincia.Enabled = False
        cmd_limpiar_datos.Enabled = True


    End Sub


    Private Sub cargarGrillaBusqueda(ByVal tabla As DataTable)

        Me.grid_barrio.Rows.Clear()
        For c = 0 To tabla.Rows.Count() - 1
            Me.grid_barrio.Rows.Add(tabla.Rows(c)("Id_Barrio"), tabla.Rows(c)("Nombre"), tabla.Rows(c)("Nombre"))
        Next

    End Sub

   
    Private Sub grid_barrio_CellContentClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles grid_barrio.CellContentClick

    End Sub
End Class