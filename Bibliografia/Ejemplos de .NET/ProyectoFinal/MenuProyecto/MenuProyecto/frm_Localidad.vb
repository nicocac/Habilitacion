Public Class frm_Localidad

    Dim bd As New acceso_a_datos
    Enum estado
        insertar
        eliminar
        modificar
    End Enum


    Dim pk_localidad_modif As String = ""
    Dim localidad_modif As String = ""
    Dim provincia_modif As String = ""
    Dim accion As estado = estado.insertar

    Private Sub cmd_nuevo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_nuevo.Click
        Me.txt_nombre.Text = ""
        Me.cmd_grabar.Enabled = True
        Me.cmd_modificar.Enabled = False
        Me.cmd_nuevo.Enabled = False
        Me.cmd_eliminar.Enabled = False
        Me.cmb_provincia.Enabled = True
        Me.cmb_provincia.SelectedValue = -1
        Me.txt_nombre.Focus()
        Me.txt_nombre.Enabled = True
        Dim pk_localidad_modif = ""
        Dim localidad_modif = ""
        Dim provincia_modif = ""
    End Sub

    Private Sub Localidad_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        bd._tabla = "Localidad"
        Me.cargarGrilla(bd.leo_tabla)


        bd._tabla = "Provincia"
        Dim tablaProvincia As DataTable = bd.leo_tabla
        carga_combo(cmb_provincia, tablaProvincia, "Id_Provincia", "Nombre")
        cmb_provincia.SelectedIndex = -1
        Me.cmb_provincia.SelectedIndex = -1
        Me.txt_nombre.Focus()
        Me.cmd_grabar.Enabled = False
        Me.cmd_eliminar.Enabled = False
        Me.cmd_modificar.Enabled = False
        Me.txt_nombre.Enabled = False
        Me.cmb_provincia.Enabled = False


    End Sub


    Private Sub Localidad_FormClosing(ByVal sender As Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles Me.FormClosing
        If MessageBox.Show("¿Esta seguro que desea salir?", "Importante", _
MessageBoxButtons.YesNo, MessageBoxIcon.Question) _
= Windows.Forms.DialogResult.Yes Then
            e.Cancel = False
        Else
            e.Cancel = True
        End If
    End Sub

    Private Sub cmd_salir_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_salir.Click
        Me.Close()
    End Sub

    Private Sub carga_combo(ByRef combo As ComboBox, ByRef datos As Data.DataTable, ByVal pk As String, ByVal descripcion As String)

        combo.DataSource = datos
        combo.ValueMember = pk
        combo.DisplayMember = descripcion
    End Sub


    Private Sub cmd_grabar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_grabar.Click
        bd._tabla = "Localidad"
        Dim tablaBarrio As DataTable = bd.leo_tabla
        Dim sql As String

        If accion = estado.insertar Then
            sql = "SELECT Nombre FROM Localidad WHERE Nombre='" & Me.txt_nombre.Text & "'and Id_Provincia='" & Me.buscar_id_provincia(Me.cmb_provincia.Text) & "'"
            Dim tabla As DataTable = bd._consulta(sql)

            If tabla.Rows.Count = 0 Then
                Dim comando As String
                Dim id_buscada As Integer = buscar_id_provincia(Me.cmb_provincia.Text)
                comando = "Insert into Localidad(Nombre, Id_Provincia) values ('" & Me.txt_nombre.Text & "', '" & id_buscada & "')"
                bd._modificar(comando)
                bd._tabla = "Localidad"
                Me.cargarGrilla(bd.leo_tabla)
                limpiar()
                MsgBox("Se cargo exitosamente")
            Else
                MsgBox("La localidad ya fue cargada en la base de datos")
            End If

        ElseIf accion = estado.modificar Then

            Dim comando As String
            Dim id_buscada As Integer = buscar_id_provincia(Me.cmb_provincia.Text)
            comando = "UPDATE Localidad SET Nombre = '" & Me.txt_nombre.Text & "', Id_Provincia = " & id_buscada & " WHERE Nombre ='" & localidad_modif & "' AND Id_Provincia = " & buscar_id_provincia(provincia_modif) & " AND Id_Localidad = " & pk_localidad_modif
            bd._modificar(comando)
            bd._tabla = "Localidad"
            Me.cargarGrilla(bd.leo_tabla)
            limpiar()
            MsgBox("Se modifico exitosamente")
            accion = estado.insertar
        End If

        cmb_provincia.Enabled = False
        cmd_nuevo.Enabled = True


    End Sub

    Private Sub cargarGrilla(ByVal tabla As DataTable)
        Dim comando_provincia As String
        Dim aux As Integer
        comando_provincia = "SELECT Nombre FROM Provincia"
        Me.grid_localidad.Rows.Clear()
        Dim tablaProvincia As DataTable = bd._consulta(comando_provincia)

        For c = 0 To tabla.Rows.Count() - 1
            aux = tabla.Rows(c)("Id_Provincia") - 1
            Me.grid_localidad.Rows.Add(tabla.Rows(c)("Id_Localidad"), tabla.Rows(c)("Nombre"), tablaProvincia.Rows(aux)("Nombre"))
        Next
    End Sub

    Private Function buscar_id_provincia(ByVal provincia As String) As Integer
        bd._tabla = "Provincia"
        Dim tablaLocalidad As DataTable = bd.leo_tabla
        Dim id As Integer = 0
        id = buscar_id(tablaLocalidad, provincia)
        Return id
    End Function

    Private Function buscar_id(ByVal tabla As DataTable, ByVal nombre As String) As Integer

        For c = 0 To tabla.Rows.Count - 1
            If tabla.Rows(c)("Nombre") = nombre Then
                Return tabla.Rows(c)("Id_Provincia")
                Exit For
            End If

        Next
        Return -1
    End Function

    Private Sub limpiar()
        Me.txt_nombre.Text = ""
        Me.cmb_provincia.SelectedValue = -1
        Me.cmb_provincia.Text = ""
        Me.cmd_grabar.Enabled = False
        Me.cmd_eliminar.Enabled = False
        Me.cmd_modificar.Enabled = False
        Me.txt_nombre.Enabled = False
        Dim pk_localidad_modif = ""
        Dim localidad_modif = ""
        Dim provincia_modif = ""
    End Sub

    Private Sub cmd_limpiar_datos_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_cancelar.Click
        limpiar()
        cmb_provincia.Enabled = False
        cmd_nuevo.Enabled = True
        txt_nombre.Enabled = False
    End Sub

    Private Sub cmd_eliminar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_eliminar.Click
        Dim sql As String
        sql = "SELECT        Id_Localidad"
        sql &= " FROM Localidad "
        sql &= " where Nombre = '" & txt_nombre.Text & "' And Id_Provincia =" & cmb_provincia.SelectedValue
        bd._tabla = "Localidad"

        Dim tabla As DataTable = bd._consulta(sql)
        Dim numero As Integer = tabla.Rows(0)("Id_Localidad")

        If MessageBox.Show("¿Esta seguro que desea eliminar la fila seleccionada?", "Atención", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button1) = Windows.Forms.DialogResult.OK Then

            bd._consulta(" delete from Localidad where Id_Localidad = " & numero)
            Me.cargarGrilla(bd.leo_tabla)
            cmd_eliminar.Enabled = False
            cmd_modificar.Enabled = False
            limpiar()
            cmd_nuevo.Enabled = True

        End If

    End Sub

    Private Sub cmd_limpiar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_limpiar.Click
        Me.txt_descripcion.Text = ""
        Me.txt_descripcion.Focus()

    End Sub

    Private Sub txt_descripcion_TextChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles txt_descripcion.TextChanged
        buscar(Me.txt_descripcion.Text)
    End Sub

    Private Sub buscar(ByVal dato As String)
        Dim tabla As New Data.DataTable
        bd._tabla = "Localidad"
        If IsNumeric(dato) Then
            tabla = Me.bd.leo_tabla("Id_Localidad = " & dato)
        Else
            tabla = Me.bd.leo_tabla("Nombre like '%" & dato & "%'")
        End If
        Me.cargarGrilla(tabla)
    End Sub

    Private Sub cmd_modificar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_modificar.Click
        accion = estado.modificar
        Me.cmb_provincia.Enabled = True
        txt_nombre.Enabled = True
        Me.cmd_modificar.Enabled = False
        Me.cmd_grabar.Enabled = True
        Me.cmd_eliminar.Enabled = False

    End Sub

    Private Sub grid_localidad_CellDoubleClick1(ByVal sender As Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles grid_localidad.CellDoubleClick
        Me.cmd_nuevo.Enabled = False
        Me.txt_nombre.Enabled = False
        Me.cmb_provincia.Enabled = False
        Me.txt_nombre.Text = Me.grid_localidad.CurrentRow.Cells("n_localidad").Value
        Me.cmb_provincia.Text = Me.grid_localidad.CurrentRow.Cells("n_provincia").Value
        Me.cmd_grabar.Enabled = False
        Me.cmd_eliminar.Enabled = True
        Me.cmd_modificar.Enabled = True
        Me.localidad_modif = Me.grid_localidad.CurrentRow.Cells("n_localidad").Value
        Me.provincia_modif = Me.grid_localidad.CurrentRow.Cells("n_provincia").Value
        Me.pk_localidad_modif = Me.grid_localidad.CurrentRow.Cells("id_localidad").Value
    End Sub
End Class