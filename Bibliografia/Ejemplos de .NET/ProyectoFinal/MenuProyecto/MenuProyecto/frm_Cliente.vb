Public Class frm_Cliente

    Dim bd As New acceso_a_datos
    Enum estado
        insertar
        modificar
    End Enum

    Enum datos
        ok
        incompletos
    End Enum

    Dim accion As estado = estado.insertar

    'CARGA DE FORMULARIO
    Private Sub frm_ABMCliente_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        deshabilitar()
        cmd_guardar.Enabled = False
        cmd_cancelar.Enabled = False
        cmd_limpiar.Enabled = True
        cmd_modificar.Enabled = False
        cmd_eliminar.Enabled = False
        cmd_agregarbarrio.Enabled = False
        cmd_agregarlocalidad.Enabled = False



        bd._tabla = "TipoDocumento"
        Dim tablaDoc As DataTable = bd.leo_tabla
        cargarCombo(cmb_tipoDocumento, tablaDoc, "Id_TipoDocumento", "Nombre")
        cmb_tipoDocumento.SelectedIndex = -1

        bd._tabla = "Provincia"
        Dim tablaProvincia As DataTable = bd.leo_tabla

        cargarCombo(cmb_provincia, tablaProvincia, "Id_Provincia", "Nombre")
        cmb_provincia.SelectedIndex = -1

        cargarGrilla()



    End Sub


    ' CIERRE DE FORMULARIO 
    Private Sub frm_ABMCliente_FormClosing(ByVal sender As Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles Me.FormClosing
        If MessageBox.Show("¿Esta seguro que desea salir?", "Importante", _
          MessageBoxButtons.YesNo, MessageBoxIcon.Question) _
      = Windows.Forms.DialogResult.Yes Then
            e.Cancel = False
        Else
            e.Cancel = True
        End If
    End Sub


    'CARGA COMBOS

    Private Sub cargarCombo(ByRef combo As ComboBox, ByRef datos As Data.DataTable, ByRef Id As String, ByVal nombre As String)

        combo.DataSource = datos
        combo.ValueMember = Id
        combo.DisplayMember = nombre
    End Sub

    Private Sub cmb_provincia_SelectionChangeCommitted(ByVal sender As Object, ByVal e As System.EventArgs) Handles cmb_provincia.SelectionChangeCommitted
        bd._tabla = "Localidad"
        Dim tablaLocalidad As DataTable = bd.leo_tabla("Id_Provincia = " & cmb_provincia.SelectedValue)
        cargarCombo(cmb_localidad, tablaLocalidad, "Id_Localidad", "Nombre")
        cmb_localidad.SelectedIndex = -1
    End Sub

    Private Sub cmb_localidad_SelectionChangeCommitted(ByVal sender As Object, ByVal e As System.EventArgs) Handles cmb_localidad.SelectionChangeCommitted
        bd._tabla = "Barrio"
        Dim tablaBarrio As DataTable = bd.leo_tabla("Id_Localidad = " & cmb_localidad.SelectedValue)
        cargarCombo(cmb_barrio, tablaBarrio, "Id_Barrio", "Nombre")
        cmb_barrio.SelectedIndex = -1

    End Sub


    'CARGA GRILLA

    Private Sub cargarGrilla()
        Dim sql As String
        sql = " select t.Nombre , c.Nro_Doc , c.Apellido , c.Nombres "
        sql &= "from Cliente c Inner join TipoDocumento t on c.Tipo_Doc = t.Id_TipoDocumento"

        Me.grid_clientes.Rows.Clear()
        Dim tabla As DataTable = bd._consulta(sql)

        For c = 0 To tabla.Rows.Count() - 1
            Me.grid_clientes.Rows.Add(tabla.Rows(c)("Nombre"), tabla.Rows(c)("Nro_Doc"), tabla.Rows(c)("Apellido") _
            , tabla.Rows(c)("Nombres"))
        Next
    End Sub


    'BOTONES

    Private Sub cmd_guardar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_guardar.Click
        If Me.validar = datos.incompletos Then
            Exit Sub
        End If

        If accion = estado.insertar Then
            If buscar_en_grilla() = True Then
                MsgBox("Ya existe un cliente cargado con ese número y tipo de documento ", MsgBoxStyle.Critical, "Corregir")
                txt_numeroDocumento.Focus()
                Exit Sub
            End If
            bd._tabla = "Cliente"
            Dim sexo As String
            Dim tablaCliente As DataTable = bd.leo_tabla
            If (Me.opt_femenino.Checked = True) Then
                sexo = "FEMENINO"
            Else
                sexo = "MASCULINO"
            End If

            Dim fechacreacion As Date
            fechacreacion = txt_fechaNacimiento.Text
            Dim fechacreacionstring = Format(fechacreacion, "MM/dd/yyyy")
            bd._insertar("Tipo_Doc =" & Me.cmb_tipoDocumento.SelectedValue & ", Nro_Doc =" & Me.txt_numeroDocumento.Text & ", Apellido =" & Me.txt_apellido.Text & ", Nombres =" & Me.txt_nombre.Text & ", Fecha_Nac =" & fechacreacionstring & ", sexo =" & sexo & ", Calle =" & Me.txt_calle.Text & ", Nro=" & Me.txt_numero.Text & ", Barrio =" & Me.cmb_barrio.SelectedValue & ", Email =" & Me.txt_email.Text & ", Telefono = " & Me.txt_telefono.Text)
            cargarGrilla()
            ' bd._insertar('Tipo_Doc = Me.cmb_tipoDocumento.SelectedValue, Nro_Doc = Me.txt_numeroDocumento.Text, Apellido = Me.txt_apellido.Text,Nombres = Me.txt_nombre.Text, Fecha_Nac = Me.txt_fechaNacimiento.Text, sexo = sexo, Calle = Me.txt_calle.Text, Nro = Me.txt_numero.Text, Barrio = Me.cmb_barrio.SelectedValue, E-mail = Me.txt_email.Text, Telefono = Me.txt_telefono.Text')
            limpiar()
            cmd_guardar.Enabled = False
            deshabilitar()
        End If

        If accion = estado.modificar Then
            bd._tabla = "Cliente"
            Dim sexo As String
            Dim tablaCliente As DataTable = bd.leo_tabla
            If (Me.opt_femenino.Checked = True) Then
                sexo = "FEMENINO"
            Else
                sexo = "MASCULINO"
            End If
            bd._modificar("update Cliente set Tipo_Doc =" & Me.cmb_tipoDocumento.SelectedValue & ", Nro_Doc =" & Me.txt_numeroDocumento.Text & ", Apellido ='" & Me.txt_apellido.Text & "', Nombres ='" & Me.txt_nombre.Text & "', Fecha_Nac ='" & Me.txt_fechaNacimiento.Text & "', sexo ='" & sexo & "', Calle ='" & Me.txt_calle.Text & "', Nro=" & Me.txt_numero.Text & ", Barrio =" & Me.cmb_barrio.SelectedValue & ", Email ='" & Me.txt_email.Text & "', Telefono = " & Me.txt_telefono.Text & " where Cliente.Tipo_Doc= " & Me.cmb_tipoDocumento.SelectedValue & " and  Cliente.Nro_Doc =" & Me.txt_numeroDocumento.Text)
            cargarGrilla()
            limpiar()
            cmd_guardar.Enabled = False
            cmd_modificar.Enabled = False
            deshabilitar()
        End If



    End Sub

    Private Sub cmd_nuevo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_nuevo.Click
        habilitar()
        cmd_guardar.Enabled = True
        cmd_cancelar.Enabled = True
        cmd_limpiar.Enabled = True
        cmd_modificar.Enabled = False
        cmd_agregarbarrio.Enabled = True
        cmd_agregarlocalidad.Enabled = True

        accion = estado.insertar
        Me.cmb_tipoDocumento.Focus()

    End Sub

    Private Sub cmd_salir_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_salir.Click
        Me.Close()
    End Sub

    Private Sub cmd_cancelar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_cancelar.Click
        If MessageBox.Show("¿Esta seguro que desea cancelar?", "Atención", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button1) = Windows.Forms.DialogResult.OK Then
            limpiar()
            deshabilitar()
            cargarGrilla()

            cmd_modificar.Enabled = False
            cmd_nuevo.Enabled = True
            cmd_cancelar.Enabled = False
            cmd_guardar.Enabled = False
            cmd_eliminar.Enabled = False
        End If

    End Sub

    Private Sub cmd_modificar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_modificar.Click
        habilitar()
        cmb_tipoDocumento.Enabled = False
        txt_numeroDocumento.Enabled = False
        cmd_guardar.Enabled = True
        cmd_cancelar.Enabled = True
        cmd_limpiar.Enabled = True
        cmd_modificar.Enabled = False
        cmd_eliminar.Enabled = False
        cmd_agregarbarrio.Enabled = True
        cmd_agregarlocalidad.Enabled = True
        accion = estado.modificar

    End Sub

    Private Sub cmd_eliminar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_eliminar.Click
        If MessageBox.Show("¿Esta seguro que desea eliminar la fila seleccionada?", "Atención", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button1) = Windows.Forms.DialogResult.OK Then
            bd._consulta(" delete from Cliente where " & cmb_tipoDocumento.SelectedValue & "=Cliente.Tipo_Doc and Cliente.Nro_Doc=" & txt_numeroDocumento.Text)
            cargarGrilla()
            cmd_eliminar.Enabled = False
            cmd_modificar.Enabled = False
            limpiar()
            deshabilitar()
            cmd_nuevo.Enabled = True

        End If
    End Sub


    'Metodo para cuando hace doble click en un cliente en la grilla




    'Metodos para blanquear , habilitar , deshabilitar, validar campos

    Private Sub limpiar()
        Me.txt_apellido.Text = ""
        Me.txt_calle.Text = ""
        Me.txt_email.Text = ""
        Me.txt_fechaNacimiento.Text = ""
        Me.txt_nombre.Text = ""
        Me.txt_numero.Text = ""
        Me.txt_numeroDocumento.Text = ""
        Me.txt_telefono.Text = ""
        cmb_barrio.SelectedIndex = -1
        cmb_localidad.SelectedIndex = -1
        cmb_provincia.SelectedIndex = -1
        cmb_tipoDocumento.SelectedIndex = -1

    End Sub

    Private Sub deshabilitar()
        txt_apellido.Enabled = False
        txt_calle.Enabled = False
        txt_email.Enabled = False
        txt_fechaNacimiento.Enabled = False
        txt_nombre.Enabled = False
        txt_numero.Enabled = False
        txt_numeroDocumento.Enabled = False
        txt_telefono.Enabled = False
        cmb_barrio.Enabled = False
        cmb_localidad.Enabled = False
        cmb_provincia.Enabled = False
        cmb_tipoDocumento.Enabled = False

        opt_femenino.Enabled = False
        opt_masculino.Enabled = False
    End Sub

    Private Sub habilitar()
        txt_apellido.Enabled = True
        txt_calle.Enabled = True
        txt_email.Enabled = True
        txt_fechaNacimiento.Enabled = True
        txt_nombre.Enabled = True
        txt_numero.Enabled = True
        txt_numeroDocumento.Enabled = True
        txt_telefono.Enabled = True
        cmb_barrio.Enabled = True
        cmb_localidad.Enabled = True
        cmb_provincia.Enabled = True
        cmb_tipoDocumento.Enabled = True
        opt_femenino.Enabled = True
        opt_masculino.Enabled = True
    End Sub

    Private Function validar()


        If cmb_tipoDocumento.SelectedIndex = -1 Then
            MsgBox("Falta seleccionar tipo de documento ", MsgBoxStyle.Critical, "Seleccionar")
            cmb_tipoDocumento.Focus()
            Return datos.incompletos
            Exit Function
        End If

        If txt_numeroDocumento.Text = "" Then
            MsgBox("Falta cargar Número de Documento ", MsgBoxStyle.Critical, "Corregir")
            txt_numeroDocumento.Focus()
            Return datos.incompletos
            Exit Function
        End If
        If txt_nombre.Text = "" Then
            MsgBox("Falta cargar el Nombre ", MsgBoxStyle.Critical, "Corregir")
            txt_nombre.Focus()
            Return datos.incompletos
            Exit Function
        End If
        If txt_apellido.Text = "" Then
            MsgBox("Falta cargar el apellido ", MsgBoxStyle.Critical, "Corregir")
            txt_apellido.Focus()
            Return datos.incompletos
            Exit Function
        End If
        If IsDate(Me.txt_fechaNacimiento.Text) = False Then
            MsgBox("no es una fecha valida", MsgBoxStyle.Critical, "Corregir")
            Me.txt_fechaNacimiento.Focus()
            Return datos.incompletos
            Exit Function
        End If
        If opt_femenino.Checked = False And opt_masculino.Checked = False Then
            MsgBox("Falta seleccionar el sexo", MsgBoxStyle.Critical, "Corregir")
            Me.GroupBox1.Focus()
            Return datos.incompletos
            Exit Function
        End If
        If txt_email.Text = "" Then
            MsgBox("Falta cargar el E-Mail ", MsgBoxStyle.Critical, "Corregir")
            txt_email.Focus()
            Return datos.incompletos
            Exit Function
        End If
        If txt_telefono.Text = "" Then
            MsgBox("Falta cargar el Número de Teléfono ", MsgBoxStyle.Critical, "Corregir")
            txt_email.Focus()
            Return datos.incompletos
            Exit Function
        End If
        If txt_calle.Text = "" Then
            MsgBox("Falta cargar la calle ", MsgBoxStyle.Critical, "Corregir")
            txt_calle.Focus()
            Return datos.incompletos
            Exit Function
        End If

        If txt_numero.Text = "" Then
            MsgBox("Falta cargar el Número de Calle ", MsgBoxStyle.Critical, "Corregir")
            txt_numero.Focus()
            Return datos.incompletos
            Exit Function
        End If
        If cmb_provincia.SelectedIndex = -1 Then
            MsgBox("Falta seleccionar la Provincia ", MsgBoxStyle.Critical, "Seleccionar")
            cmb_provincia.Focus()
            Return datos.incompletos
            Exit Function
        End If
        If cmb_localidad.SelectedIndex = -1 Then
            MsgBox("Falta seleccionar la Localidad ", MsgBoxStyle.Critical, "Seleccionar")
            cmb_localidad.Focus()
            Return datos.incompletos
            Exit Function
        End If
        If cmb_barrio.SelectedIndex = -1 Then
            MsgBox("Falta seleccionar el Barrio ", MsgBoxStyle.Critical, "Seleccionar")
            cmb_barrio.Focus()
            Return datos.incompletos
            Exit Function
        End If
        Return datos.ok

    End Function

    'Metodo que valida ingreso numerico

    Private Sub Varios_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles txt_numeroDocumento.KeyPress, txt_telefono.KeyPress, txt_numero.KeyPress
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

    Private Sub Letra_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles txt_apellido.KeyPress, txt_nombre.KeyPress, txt_calle.KeyPress
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

    Private Function buscar_en_grilla() As Boolean
        Dim sql As String
        sql = " select * "
        sql &= "from Cliente c  "
        sql &= " where c.Nro_Doc =" & Me.txt_numeroDocumento.Text & " and c.Tipo_Doc=" & cmb_tipoDocumento.SelectedValue
        Dim tabla As New DataTable
        tabla = bd._consulta(sql)
        If tabla.Rows.Count = 1 Then
            Return True
        Else
            Return False
        End If

    End Function


    
    Private Sub grid_clientes_CellDoubleClick1(ByVal sender As Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles grid_clientes.CellDoubleClick
        Dim fila As Integer
        Dim tipoDoc As String
        Dim numDoc As String
        Dim tablaCliente As New DataTable
        cmd_modificar.Enabled = True
        cmd_cancelar.Enabled = True
        cmd_eliminar.Enabled = True
        cmd_nuevo.Enabled = False
        cmd_guardar.Enabled = False
        cmd_agregarbarrio.Enabled = False
        cmd_agregarlocalidad.Enabled = False
        'fila = Me.grillaVehiculo.CurrentRow.Index
        'patente = Me.grillaVehiculo.Rows(fila).Cells(0).Value()
        deshabilitar()
        fila = grid_clientes.CurrentRow.Index
        tipoDoc = grid_clientes.Rows(fila).Cells(0).Value()
        numDoc = grid_clientes.Rows(fila).Cells(1).Value()

        Dim sql As String
        sql = " select * "
        sql &= "from Cliente c Inner join TipoDocumento t on c.Tipo_Doc = t.Id_TipoDocumento "
        sql &= " inner join Barrio b on c.Barrio = b.Id_Barrio"
        sql &= " inner join Localidad l on b.Id_Localidad = l.Id_Localidad"
        sql &= " inner join Provincia p on l.Id_Provincia = p.Id_Provincia"
        sql &= " where c.Nro_Doc =" & numDoc & " and t.Nombre= '" & tipoDoc & "'"

        tablaCliente = bd._consulta(sql)

        cmb_tipoDocumento.SelectedValue = tablaCliente.Rows(0)("Tipo_Doc")
        txt_numeroDocumento.Text = tablaCliente.Rows(0)("Nro_Doc")
        txt_apellido.Text = tablaCliente.Rows(0)("Apellido")
        txt_nombre.Text = tablaCliente.Rows(0)("Nombres")
        txt_fechaNacimiento.Text = tablaCliente.Rows(0)("Fecha_Nac")

        Dim sexopata As String = tablaCliente.Rows(0)("Sexo")
        If sexopata = "MASCULINO " Then
            'Tener el espacio en cuenta

            opt_masculino.Checked = True
        Else
            opt_femenino.Checked = True

        End If

        txt_calle.Text = tablaCliente.Rows(0)("Calle")
        txt_numero.Text = tablaCliente.Rows(0)("Nro")
        cmb_provincia.SelectedValue = tablaCliente.Rows(0)("Id_Provincia")

        bd._tabla = "Localidad"
        Dim tablaLocalidad As DataTable = bd.leo_tabla("Id_Provincia = " & tablaCliente.Rows(0)("Id_Provincia"))
        cargarCombo(cmb_localidad, tablaLocalidad, "Id_Localidad", "Nombre")
        cmb_localidad.SelectedValue = tablaCliente.Rows(0)("Id_Localidad")

        bd._tabla = "Barrio"
        Dim tablaBarrio As DataTable = bd.leo_tabla("Id_Localidad = " & tablaCliente.Rows(0)("Id_Localidad"))
        cargarCombo(cmb_barrio, tablaBarrio, "Id_Barrio", "Nombre")

        cmb_barrio.SelectedValue = tablaCliente.Rows(0)("Id_Barrio")


        If IsDBNull(tablaCliente.Rows(0)("Email")) Then
        Else
            txt_email.Text = tablaCliente.Rows(0)("Email")
        End If

        txt_telefono.Text = tablaCliente.Rows(0)("Telefono")
    End Sub

    'Busqueda
    Private Sub cargar_Grilla_Busqueda(ByVal tabla As DataTable)
        Me.grid_clientes.Rows.Clear()
        For c = 0 To tabla.Rows.Count() - 1
            Me.grid_clientes.Rows.Add(tabla.Rows(c)("Nombre"), tabla.Rows(c)("Nro_Doc"), tabla.Rows(c)("Apellido") _
                      , tabla.Rows(c)("Nombres"))
        Next
    End Sub

    Private Sub buscar(ByVal dato As String)
        Dim tabla As New Data.DataTable
        bd._tabla = "Cliente"
        Dim sql As String
       
        Me.grid_clientes.Rows.Clear()

        If IsNumeric(dato) Then
            sql = " select t.Nombre , c.Nro_Doc , c.Apellido , c.Nombres "
            sql &= "from Cliente c Inner join TipoDocumento t on c.Tipo_Doc = t.Id_TipoDocumento where c.Nro_Doc like '" & dato & "%'"
            tabla = bd._consulta(sql)
        Else
            sql = " select t.Nombre , c.Nro_Doc , c.Apellido , c.Nombres "
            sql &= "from Cliente c Inner join TipoDocumento t on c.Tipo_Doc = t.Id_TipoDocumento where Apellido like '" & dato & "%'"
            tabla = bd._consulta(sql)

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