Public Class frm_producto
    Dim bd As New acceso_a_datos

    Dim string_conexion As String = "Data Source=CONSTANZA-PC\Sqlexpress;Initial Catalog=ElCampito;Integrated Security=True"

    Enum estado
        insertar
        eliminar
        modificar
    End Enum
    Enum terminado
        aprobado
        rechazado
    End Enum

    Dim accion As estado = estado.insertar

    Private Sub frm_producto_FormClosing(ByVal sender As Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles Me.FormClosing
        If MessageBox.Show("¿Esta seguro que desea salir?", "Importante", _
          MessageBoxButtons.YesNo, MessageBoxIcon.Question) _
      = Windows.Forms.DialogResult.Yes Then
            e.Cancel = False
        Else
            e.Cancel = True
        End If

    End Sub



    Private Sub Producto_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
      
        string_conexion = bd._CadenaConexion
        cmd_grabar.Enabled = False
        cmd_modificar.Enabled = False
        cmd_cancelar.Enabled = False
        cmd_eliminar.Enabled = False
        Me.cmd_limpiar.Enabled = False
        cargarGrilla()

    End Sub

    Private Sub cargarGrilla()
        Dim sql As String
        sql = "SELECT Id_Producto, Nombre, Precio, Descripcion, Cant_Stock "
        sql &= "from Productos"
        Me.grid_producto.Rows.Clear()
        Dim tabla As DataTable = bd._consulta(sql)

        For c = 0 To tabla.Rows.Count() - 1
            Me.grid_producto.Rows.Add(tabla.Rows(c)("Id_Producto"), tabla.Rows(c)("Nombre"), tabla.Rows(c)("precio"), tabla.Rows(c)("Descripcion"), tabla.Rows(c)("Cant_Stock"))
        Next
    End Sub

    Private Sub cmd_salir_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_salir.Click
        Me.Close()
    End Sub

    Private Sub grid_producto_CellContentClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles grid_producto.CellContentClick

    End Sub

    Private Sub cmd_grabar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_grabar.Click

        If validar() = True Then

            If accion = estado.insertar Then
                If Validar_existencia() = terminado.aprobado Then

                    insertar()

                    cargarGrilla()
                    MessageBox.Show("Se grabó exitosamente", "Importante", MessageBoxButtons.OK, MessageBoxIcon.Information)

                Else
                    MsgBox("Este producto ya esta cargado", MsgBoxStyle.Critical, "Importante")
                    Limpiar()
                    Me.txt_nombre.Focus()

                End If
            End If

                If accion = estado.modificar Then
                modificar()
                cargarGrilla()
                    MessageBox.Show("Se modificó exitosamente", "Importante", MessageBoxButtons.OK, MessageBoxIcon.Information)
                End If
                cmd_modificar.Enabled = False
                cmd_grabar.Enabled = False
                deshabilitar()

            End If


    End Sub
    Private Sub insertar()
        ' Stream usado como buffer
        Dim ms As New System.IO.MemoryStream
        ' Se guarda la imagen en el buffer
        PictureBox1.Image.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg)
        'Se extraen los bytes del buffer para asignarlos como valor para el  parámetro.

        Dim conexion As New Data.SqlClient.SqlConnection
        Dim cmd As New Data.SqlClient.SqlCommand
        conexion.ConnectionString = string_conexion

        cmd.Connection = conexion

        cmd.CommandText = "INSERT INTO Productos values (@Nombre, @Precio, @Descripcion, @Color, @Material_Principal, @Peso, @Largo, @Ancho, @Alto, @Cant_Stock, @Periodo_Garantia, @Imagen) "
        cmd.Parameters.Add("@Nombre", System.Data.SqlDbType.VarChar)
        cmd.Parameters.Add("@Precio", System.Data.SqlDbType.Float)
        cmd.Parameters.Add("@Descripcion", System.Data.SqlDbType.VarChar)
        cmd.Parameters.Add("@Color", System.Data.SqlDbType.VarChar)
        cmd.Parameters.Add("@Material_Principal", System.Data.SqlDbType.VarChar)
        cmd.Parameters.Add("@Peso", System.Data.SqlDbType.Float)
        cmd.Parameters.Add("@Largo", System.Data.SqlDbType.Float)
        cmd.Parameters.Add("@Ancho", System.Data.SqlDbType.Float)
        cmd.Parameters.Add("@Alto", System.Data.SqlDbType.Float)
        cmd.Parameters.Add("@Cant_Stock", System.Data.SqlDbType.Int)
        cmd.Parameters.Add("@Periodo_Garantia", System.Data.SqlDbType.NChar)
        cmd.Parameters.Add("@Imagen", System.Data.SqlDbType.Image)

        cmd.Parameters("@Nombre").Value = txt_nombre.Text
        cmd.Parameters("@Precio").Value = Convert.ToDouble(txt_precio.Text)
        cmd.Parameters("@Descripcion").Value = txt_descripcion.Text
        cmd.Parameters("@Color").Value = txt_color.Text
        cmd.Parameters("@Material_Principal").Value = txt_material_principal.Text
        cmd.Parameters("@Peso").Value = Convert.ToDouble(txt_peso.Text)
        cmd.Parameters("@Largo").Value = Convert.ToDouble(txt_largo.Text)
        cmd.Parameters("@Ancho").Value = Convert.ToDouble(txt_ancho.Text)
        cmd.Parameters("@Alto").Value = Convert.ToDouble(txt_alto.Text)
        cmd.Parameters("@Cant_Stock").Value = 0
        cmd.Parameters("@Periodo_Garantia").Value = txt_garantia.Text
        cmd.Parameters("@Imagen").Value = ms.GetBuffer

        Dim consulta As String = ""
        conexion.ConnectionString = Me.string_conexion
        conexion.Open()
        cmd.ExecuteNonQuery()
        conexion.Close()
    End Sub

    Private Sub modificar()
        Dim id As Integer = Me.grid_producto.CurrentRow.Cells("Id_Producto").Value

        ' Stream usado como buffer
        Dim ms As New System.IO.MemoryStream
        ' Se guarda la imagen en el buffer
        PictureBox1.Image.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg)
        'Se extraen los bytes del buffer para asignarlos como valor para el  parámetro.


        Dim conexion As New Data.SqlClient.SqlConnection
        Dim cmd As New Data.SqlClient.SqlCommand
        conexion.ConnectionString = string_conexion

        cmd.Connection = conexion

        cmd.CommandText = "Update  Productos set Nombre=@Nombre, Precio=@Precio, Descripcion=@Descripcion, Color=@Color, Material_Principal=@Material_Principal, Peso=@Peso, Largo=@Largo, Ancho=@Ancho, Alto=@Alto, Periodo_Garantia=@Periodo_Garantia, Imagen=@Imagen  WHERE " & id & " = Productos.Id_Producto"
        cmd.Parameters.Add("@Nombre", System.Data.SqlDbType.VarChar)
        cmd.Parameters.Add("@Precio", System.Data.SqlDbType.Float)
        cmd.Parameters.Add("@Descripcion", System.Data.SqlDbType.VarChar)
        cmd.Parameters.Add("@Color", System.Data.SqlDbType.VarChar)
        cmd.Parameters.Add("@Material_Principal", System.Data.SqlDbType.VarChar)
        cmd.Parameters.Add("@Peso", System.Data.SqlDbType.Float)
        cmd.Parameters.Add("@Largo", System.Data.SqlDbType.Float)
        cmd.Parameters.Add("@Ancho", System.Data.SqlDbType.Float)
        cmd.Parameters.Add("@Alto", System.Data.SqlDbType.Float)
        ' cmd.Parameters.Add("@Cant_Stock", System.Data.SqlDbType.Int)
        cmd.Parameters.Add("@Periodo_Garantia", System.Data.SqlDbType.NChar)
        cmd.Parameters.Add("@Imagen", System.Data.SqlDbType.Image)

        cmd.Parameters("@Nombre").Value = txt_nombre.Text
        cmd.Parameters("@Precio").Value = Convert.ToDouble(txt_precio.Text)
        cmd.Parameters("@Descripcion").Value = txt_descripcion.Text
        cmd.Parameters("@Color").Value = txt_color.Text
        cmd.Parameters("@Material_Principal").Value = txt_material_principal.Text
        cmd.Parameters("@Peso").Value = Convert.ToDouble(txt_peso.Text)
        cmd.Parameters("@Largo").Value = Convert.ToDouble(txt_largo.Text)
        cmd.Parameters("@Ancho").Value = Convert.ToDouble(txt_ancho.Text)
        cmd.Parameters("@Alto").Value = Convert.ToDouble(txt_alto.Text)
        ' cmd.Parameters("@Cant_Stock").Value = 0
        cmd.Parameters("@Periodo_Garantia").Value = txt_garantia.Text
        cmd.Parameters("@Imagen").Value = ms.GetBuffer

        Dim consulta As String = ""
        conexion.ConnectionString = Me.string_conexion
        conexion.Open()
        cmd.ExecuteNonQuery()
        conexion.Close()
    End Sub

    Private Function Validar_existencia() As terminado
        Dim sql As String
        sql = (" select * " & _
                " from Productos" & _
                " where  Productos.Nombre = '" & Me.txt_nombre.Text & "'")
        Dim tabla As New DataTable
        tabla = bd._consulta(sql)
        If tabla.Rows.Count = 1 Then
            Return terminado.rechazado
        Else
            Return terminado.aprobado
        End If

    End Function

    Private Sub cmd_nuevo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_nuevo.Click
  
        Habilitar()
        Limpiar()

        cmd_grabar.Enabled = True
        cmd_modificar.Enabled = False
        cmd_cancelar.Enabled = True

        accion = estado.insertar
    End Sub

    Private Sub txt_nro_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles txt_precio.KeyPress, txt_alto.KeyPress, txt_ancho.KeyPress, txt_largo.KeyPress, txt_cantidad.KeyPress, txt_peso.KeyPress
        Select Case Asc(e.KeyChar)
            Case 4, 24, 4, 19, 127, 13, 9, 15, 14, 46, 8
                Exit Sub
        End Select
        If IsNumeric(e.KeyChar) = False Then
            MsgBox("Este carater no es un numero ( " + e.KeyChar + " )", vbCritical, "Importante")
            e.KeyChar = ""
        End If
    End Sub

    Private Function validar() As Boolean
        If Me.txt_nombre.Text = "" Then
            MsgBox("El Nombre no puede estar vacio", MsgBoxStyle.Critical, "Importante")
            Me.txt_nombre.Focus()
            Return False
            Exit Function
        End If
        If Me.txt_precio.Text = "" Then
            MsgBox("El Precio no puede estar vacio", MsgBoxStyle.Critical, "Importante")
            Me.txt_precio.Focus()
            Return False
            Exit Function
        End If
        If Me.txt_descripcion.Text = "" Then
            MsgBox("La Descripción no puede estar vacia", MsgBoxStyle.Critical, "Importante")
            Me.txt_descripcion.Focus()
            Return False
            Exit Function

        End If
        Return True
    End Function

    Private Sub cmd_eliminar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_eliminar.Click
        Dim id As Integer = Me.grid_producto.CurrentRow.Cells("Id_Producto").Value
        If MessageBox.Show("¿Esta seguro que desea eliminar la fila seleccionada?", "Atención", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button1) = Windows.Forms.DialogResult.OK Then
            bd._consulta(" DELETE FROM Productos WHERE " & id & " = Productos.Id_Producto")

            cargarGrilla()
            cmd_eliminar.Enabled = False
            deshabilitar()
            cmd_nuevo.Enabled = True

        End If
    End Sub

   

    Private Sub grid_producto_CellDoubleClick(ByVal sender As Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles grid_producto.CellDoubleClick
        Dim id As Integer = Me.grid_producto.CurrentRow.Cells("Id_Producto").Value
        Dim tablaProductos As New DataTable
        deshabilitar()
        cmd_eliminar.Enabled = True
        cmd_modificar.Enabled = True
        cmd_cancelar.Enabled = True
        cmd_nuevo.Enabled = False
        cmd_grabar.Enabled = False


        Dim sql As String
        sql = "SELECT *"
        sql &= " FROM Productos P"
        sql &= " WHERE P.Id_producto = '" & id & "'"

        tablaProductos = bd._consulta(sql)

        txt_nombre.Text = tablaProductos.Rows(0)("Nombre")
        txt_precio.Text = tablaProductos.Rows(0)("Precio")
        txt_descripcion.Text = tablaProductos.Rows(0)("Descripcion")
        txt_color.Text = tablaProductos.Rows(0)("Color")
        txt_material_principal.Text = tablaProductos.Rows(0)("Material_Principal")
        txt_peso.Text = tablaProductos.Rows(0)("Peso")
        txt_garantia.Text = tablaProductos.Rows(0)("Periodo_Garantia")
        txt_largo.Text = tablaProductos.Rows(0)("Largo")
        txt_ancho.Text = tablaProductos.Rows(0)("Ancho")
        txt_cantidad.Text = tablaProductos.Rows(0)("Cant_Stock")
        txt_alto.Text = tablaProductos.Rows(0)("Alto")

        ' El campo Imagen primero se almacena en un buffer
        Dim bufferr As Byte() = tablaProductos.Rows(0)("Imagen")
        ' Se crea un MemoryStream a partir de ese buffer
        Dim ms As New System.IO.MemoryStream(bufferr)
        ' Se utiliza el MemoryStream para extraer la imagen
        PictureBox1.Image = Image.FromStream(ms)

        accion = estado.modificar
    End Sub

    Private Sub cmd_cancelar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_cancelar.Click
        If MessageBox.Show("¿Esta seguro que desea cancelar?", "Atención", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button1) = Windows.Forms.DialogResult.OK Then
            Limpiar()
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
        Habilitar()
        cmd_grabar.Enabled = True
        cmd_cancelar.Enabled = True
        cmd_modificar.Enabled = False
        cmd_eliminar.Enabled = False
        cmd_cancelar.Enabled = True

        accion = estado.modificar
    End Sub

   

   
    Private Sub ToolTip1_Popup(ByVal sender As System.Object, ByVal e As System.Windows.Forms.PopupEventArgs) Handles ToolTip1.Popup

    End Sub

    Private Sub cmd_buscarImagen_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_buscarImagen.Click
        OpenFileDialog1 = New OpenFileDialog()
        Dim result As DialogResult
        result = OpenFileDialog1.ShowDialog()
        If result = DialogResult.OK Then
            PictureBox1.Image = Image.FromFile(OpenFileDialog1.FileName)
        End If
    End Sub

    'METODOS DE LIMPIAR, HABILITAR
    Private Sub Habilitar()
        txt_nombre.Enabled = True
        txt_precio.Enabled = True
        txt_descripcion.Enabled = True
        txt_color.Enabled = True
        txt_peso.Enabled = True
        txt_largo.Enabled = True
        txt_alto.Enabled = True
        txt_ancho.Enabled = True
        txt_garantia.Enabled = True
        txt_material_principal.Enabled = True
        cmd_buscarImagen.Enabled = True
    End Sub

    Private Sub Limpiar()
        Dim objeto As Control
        PictureBox1.Image = PictureBox1.InitialImage
        For Each objeto In Me.Controls
            If TypeOf objeto Is TextBox Then objeto.Text = ""
        Next
    End Sub

    Private Sub deshabilitar()
        txt_nombre.Enabled = False
        txt_precio.Enabled = False
        txt_descripcion.Enabled = False
        txt_color.Enabled = False
        txt_peso.Enabled = False
        txt_largo.Enabled = False
        txt_alto.Enabled = False
        txt_ancho.Enabled = False
        txt_cantidad.Enabled = False
        txt_garantia.Enabled = False
        txt_material_principal.Enabled = False
        txt_cantidad.Enabled = False
        cmd_buscarImagen.Enabled = False
    End Sub
    'Busqueda
    Private Sub cargar_Grilla_Busqueda(ByVal tabla As DataTable)
        Me.grid_producto.Rows.Clear()
        For c = 0 To tabla.Rows.Count() - 1
            Me.grid_producto.Rows.Add(tabla.Rows(c)("Id_Producto"), tabla.Rows(c)("Nombre"), tabla.Rows(c)("precio"), _
            tabla.Rows(c)("Descripcion"), tabla.Rows(c)("Cant_Stock"))
        Next
    End Sub

    Private Sub buscar(ByVal dato As String)
        Dim tabla As New Data.DataTable
        bd._tabla = "Productos"
        If IsNumeric(dato) Then
            tabla = Me.bd.leo_tabla("Id_Producto = " & dato)
        Else
            tabla = Me.bd.leo_tabla("Nombre like '%" & dato & "%'")
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

End Class