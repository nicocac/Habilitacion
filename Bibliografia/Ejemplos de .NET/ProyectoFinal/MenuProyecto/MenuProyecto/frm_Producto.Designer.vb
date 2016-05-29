<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frm_producto
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(frm_producto))
        Me.txt_nombre = New System.Windows.Forms.TextBox()
        Me.txt_precio = New System.Windows.Forms.TextBox()
        Me.txt_descripcion = New System.Windows.Forms.TextBox()
        Me.txt_color = New System.Windows.Forms.TextBox()
        Me.txt_material_principal = New System.Windows.Forms.TextBox()
        Me.txt_peso = New System.Windows.Forms.TextBox()
        Me.txt_largo = New System.Windows.Forms.TextBox()
        Me.txt_ancho = New System.Windows.Forms.TextBox()
        Me.txt_alto = New System.Windows.Forms.TextBox()
        Me.txt_cantidad = New System.Windows.Forms.TextBox()
        Me.txt_garantia = New System.Windows.Forms.TextBox()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.Label7 = New System.Windows.Forms.Label()
        Me.Label8 = New System.Windows.Forms.Label()
        Me.Label9 = New System.Windows.Forms.Label()
        Me.Label10 = New System.Windows.Forms.Label()
        Me.Label11 = New System.Windows.Forms.Label()
        Me.grid_producto = New System.Windows.Forms.DataGridView()
        Me.Id_Producto = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Nombre = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.precio = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Descripcion = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Cant_Stock = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Label12 = New System.Windows.Forms.Label()
        Me.txt_buscar = New System.Windows.Forms.TextBox()
        Me.Label13 = New System.Windows.Forms.Label()
        Me.GroupBox1 = New System.Windows.Forms.GroupBox()
        Me.cmd_limpiar = New System.Windows.Forms.Button()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.cmd_salir = New System.Windows.Forms.Button()
        Me.cmd_modificar = New System.Windows.Forms.Button()
        Me.cmd_cancelar = New System.Windows.Forms.Button()
        Me.cmd_eliminar = New System.Windows.Forms.Button()
        Me.cmd_grabar = New System.Windows.Forms.Button()
        Me.cmd_nuevo = New System.Windows.Forms.Button()
        Me.cmd_buscarImagen = New System.Windows.Forms.Button()
        Me.Label14 = New System.Windows.Forms.Label()
        Me.OpenFileDialog1 = New System.Windows.Forms.OpenFileDialog()
        Me.PictureBox1 = New System.Windows.Forms.PictureBox()
        CType(Me.grid_producto, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.GroupBox1.SuspendLayout()
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'txt_nombre
        '
        Me.txt_nombre.Location = New System.Drawing.Point(115, 38)
        Me.txt_nombre.Name = "txt_nombre"
        Me.txt_nombre.Size = New System.Drawing.Size(149, 20)
        Me.txt_nombre.TabIndex = 0
        '
        'txt_precio
        '
        Me.txt_precio.Location = New System.Drawing.Point(115, 64)
        Me.txt_precio.Name = "txt_precio"
        Me.txt_precio.Size = New System.Drawing.Size(149, 20)
        Me.txt_precio.TabIndex = 1
        '
        'txt_descripcion
        '
        Me.txt_descripcion.Location = New System.Drawing.Point(115, 90)
        Me.txt_descripcion.Name = "txt_descripcion"
        Me.txt_descripcion.Size = New System.Drawing.Size(149, 20)
        Me.txt_descripcion.TabIndex = 2
        '
        'txt_color
        '
        Me.txt_color.Location = New System.Drawing.Point(115, 116)
        Me.txt_color.Name = "txt_color"
        Me.txt_color.Size = New System.Drawing.Size(149, 20)
        Me.txt_color.TabIndex = 3
        '
        'txt_material_principal
        '
        Me.txt_material_principal.Location = New System.Drawing.Point(115, 142)
        Me.txt_material_principal.Name = "txt_material_principal"
        Me.txt_material_principal.Size = New System.Drawing.Size(149, 20)
        Me.txt_material_principal.TabIndex = 4
        '
        'txt_peso
        '
        Me.txt_peso.Location = New System.Drawing.Point(115, 168)
        Me.txt_peso.Name = "txt_peso"
        Me.txt_peso.Size = New System.Drawing.Size(149, 20)
        Me.txt_peso.TabIndex = 5
        '
        'txt_largo
        '
        Me.txt_largo.Location = New System.Drawing.Point(115, 194)
        Me.txt_largo.Name = "txt_largo"
        Me.txt_largo.Size = New System.Drawing.Size(149, 20)
        Me.txt_largo.TabIndex = 6
        '
        'txt_ancho
        '
        Me.txt_ancho.Location = New System.Drawing.Point(115, 220)
        Me.txt_ancho.Name = "txt_ancho"
        Me.txt_ancho.Size = New System.Drawing.Size(149, 20)
        Me.txt_ancho.TabIndex = 7
        '
        'txt_alto
        '
        Me.txt_alto.Location = New System.Drawing.Point(115, 246)
        Me.txt_alto.Name = "txt_alto"
        Me.txt_alto.Size = New System.Drawing.Size(149, 20)
        Me.txt_alto.TabIndex = 8
        '
        'txt_cantidad
        '
        Me.txt_cantidad.Location = New System.Drawing.Point(115, 272)
        Me.txt_cantidad.Name = "txt_cantidad"
        Me.txt_cantidad.Size = New System.Drawing.Size(149, 20)
        Me.txt_cantidad.TabIndex = 9
        '
        'txt_garantia
        '
        Me.txt_garantia.Location = New System.Drawing.Point(115, 298)
        Me.txt_garantia.Name = "txt_garantia"
        Me.txt_garantia.Size = New System.Drawing.Size(149, 20)
        Me.txt_garantia.TabIndex = 10
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(65, 41)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(44, 13)
        Me.Label1.TabIndex = 2
        Me.Label1.Text = "Nombre"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(72, 67)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(37, 13)
        Me.Label2.TabIndex = 2
        Me.Label2.Text = "Precio"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(46, 93)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(63, 13)
        Me.Label3.TabIndex = 2
        Me.Label3.Text = "Descripción"
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(78, 119)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(31, 13)
        Me.Label4.TabIndex = 2
        Me.Label4.Text = "Color"
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(22, 145)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(87, 13)
        Me.Label5.TabIndex = 2
        Me.Label5.Text = "Material Principal"
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.Location = New System.Drawing.Point(18, 171)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(91, 13)
        Me.Label6.TabIndex = 2
        Me.Label6.Text = "Peso (Kilogramos)"
        '
        'Label7
        '
        Me.Label7.AutoSize = True
        Me.Label7.Location = New System.Drawing.Point(52, 197)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(57, 13)
        Me.Label7.TabIndex = 2
        Me.Label7.Text = "Largo (cm)"
        '
        'Label8
        '
        Me.Label8.AutoSize = True
        Me.Label8.Location = New System.Drawing.Point(48, 223)
        Me.Label8.Name = "Label8"
        Me.Label8.Size = New System.Drawing.Size(61, 13)
        Me.Label8.TabIndex = 2
        Me.Label8.Text = "Ancho (cm)"
        '
        'Label9
        '
        Me.Label9.AutoSize = True
        Me.Label9.Location = New System.Drawing.Point(61, 249)
        Me.Label9.Name = "Label9"
        Me.Label9.Size = New System.Drawing.Size(48, 13)
        Me.Label9.TabIndex = 2
        Me.Label9.Text = "Alto (cm)"
        '
        'Label10
        '
        Me.Label10.AutoSize = True
        Me.Label10.Location = New System.Drawing.Point(29, 275)
        Me.Label10.Name = "Label10"
        Me.Label10.Size = New System.Drawing.Size(80, 13)
        Me.Label10.TabIndex = 2
        Me.Label10.Text = "Cantidad Stock"
        '
        'Label11
        '
        Me.Label11.AutoSize = True
        Me.Label11.Location = New System.Drawing.Point(10, 301)
        Me.Label11.Name = "Label11"
        Me.Label11.Size = New System.Drawing.Size(99, 13)
        Me.Label11.TabIndex = 2
        Me.Label11.Text = "Periodo de garantia"
        '
        'grid_producto
        '
        Me.grid_producto.AllowUserToAddRows = False
        Me.grid_producto.AllowUserToDeleteRows = False
        Me.grid_producto.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.grid_producto.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.Id_Producto, Me.Nombre, Me.precio, Me.Descripcion, Me.Cant_Stock})
        Me.grid_producto.Location = New System.Drawing.Point(496, 38)
        Me.grid_producto.Name = "grid_producto"
        Me.grid_producto.ReadOnly = True
        Me.grid_producto.Size = New System.Drawing.Size(421, 280)
        Me.grid_producto.TabIndex = 18
        '
        'Id_Producto
        '
        Me.Id_Producto.HeaderText = "Código"
        Me.Id_Producto.Name = "Id_Producto"
        Me.Id_Producto.ReadOnly = True
        Me.Id_Producto.Width = 50
        '
        'Nombre
        '
        Me.Nombre.HeaderText = "Nombre"
        Me.Nombre.Name = "Nombre"
        Me.Nombre.ReadOnly = True
        Me.Nombre.Width = 120
        '
        'precio
        '
        Me.precio.HeaderText = "Precio"
        Me.precio.Name = "precio"
        Me.precio.ReadOnly = True
        '
        'Descripcion
        '
        Me.Descripcion.HeaderText = "Descripción"
        Me.Descripcion.Name = "Descripcion"
        Me.Descripcion.ReadOnly = True
        Me.Descripcion.Width = 120
        '
        'Cant_Stock
        '
        Me.Cant_Stock.HeaderText = "Cantidad Stock"
        Me.Cant_Stock.Name = "Cant_Stock"
        Me.Cant_Stock.ReadOnly = True
        Me.Cant_Stock.Width = 110
        '
        'Label12
        '
        Me.Label12.AutoSize = True
        Me.Label12.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, CType((System.Drawing.FontStyle.Bold Or System.Drawing.FontStyle.Underline), System.Drawing.FontStyle), System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label12.Location = New System.Drawing.Point(493, 19)
        Me.Label12.Name = "Label12"
        Me.Label12.Size = New System.Drawing.Size(78, 16)
        Me.Label12.TabIndex = 5
        Me.Label12.Text = "Productos"
        '
        'txt_buscar
        '
        Me.txt_buscar.Location = New System.Drawing.Point(102, 26)
        Me.txt_buscar.Name = "txt_buscar"
        Me.txt_buscar.Size = New System.Drawing.Size(175, 20)
        Me.txt_buscar.TabIndex = 16
        '
        'Label13
        '
        Me.Label13.AutoSize = True
        Me.Label13.Location = New System.Drawing.Point(6, 29)
        Me.Label13.Name = "Label13"
        Me.Label13.Size = New System.Drawing.Size(90, 13)
        Me.Label13.TabIndex = 17
        Me.Label13.Text = "Nombre Producto"
        '
        'GroupBox1
        '
        Me.GroupBox1.Controls.Add(Me.Label13)
        Me.GroupBox1.Controls.Add(Me.cmd_limpiar)
        Me.GroupBox1.Controls.Add(Me.txt_buscar)
        Me.GroupBox1.Location = New System.Drawing.Point(388, 341)
        Me.GroupBox1.Name = "GroupBox1"
        Me.GroupBox1.Size = New System.Drawing.Size(331, 58)
        Me.GroupBox1.TabIndex = 20
        Me.GroupBox1.TabStop = False
        Me.GroupBox1.Text = "Buscador"
        '
        'cmd_limpiar
        '
        Me.cmd_limpiar.Image = Global.MenuProyecto.My.Resources.Resources.icono_x
        Me.cmd_limpiar.Location = New System.Drawing.Point(283, 26)
        Me.cmd_limpiar.Name = "cmd_limpiar"
        Me.cmd_limpiar.Size = New System.Drawing.Size(20, 20)
        Me.cmd_limpiar.TabIndex = 17
        Me.ToolTip1.SetToolTip(Me.cmd_limpiar, "Buscar")
        Me.cmd_limpiar.UseVisualStyleBackColor = True
        '
        'ToolTip1
        '
        '
        'cmd_salir
        '
        Me.cmd_salir.Image = CType(resources.GetObject("cmd_salir.Image"), System.Drawing.Image)
        Me.cmd_salir.Location = New System.Drawing.Point(872, 354)
        Me.cmd_salir.Name = "cmd_salir"
        Me.cmd_salir.Size = New System.Drawing.Size(45, 45)
        Me.cmd_salir.TabIndex = 19
        Me.ToolTip1.SetToolTip(Me.cmd_salir, "Salir")
        Me.cmd_salir.UseVisualStyleBackColor = True
        '
        'cmd_modificar
        '
        Me.cmd_modificar.Image = Global.MenuProyecto.My.Resources.Resources.Icono_modificar
        Me.cmd_modificar.Location = New System.Drawing.Point(119, 354)
        Me.cmd_modificar.Name = "cmd_modificar"
        Me.cmd_modificar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_modificar.TabIndex = 13
        Me.ToolTip1.SetToolTip(Me.cmd_modificar, "Modificar")
        Me.cmd_modificar.UseVisualStyleBackColor = True
        '
        'cmd_cancelar
        '
        Me.cmd_cancelar.Image = Global.MenuProyecto.My.Resources.Resources.splashcancelar
        Me.cmd_cancelar.Location = New System.Drawing.Point(221, 354)
        Me.cmd_cancelar.Name = "cmd_cancelar"
        Me.cmd_cancelar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_cancelar.TabIndex = 15
        Me.ToolTip1.SetToolTip(Me.cmd_cancelar, "Cancelar")
        Me.cmd_cancelar.UseVisualStyleBackColor = True
        '
        'cmd_eliminar
        '
        Me.cmd_eliminar.Image = Global.MenuProyecto.My.Resources.Resources.Icono_eliminar2
        Me.cmd_eliminar.Location = New System.Drawing.Point(170, 354)
        Me.cmd_eliminar.Name = "cmd_eliminar"
        Me.cmd_eliminar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_eliminar.TabIndex = 14
        Me.ToolTip1.SetToolTip(Me.cmd_eliminar, "Eliminar")
        Me.cmd_eliminar.UseVisualStyleBackColor = True
        '
        'cmd_grabar
        '
        Me.cmd_grabar.Image = CType(resources.GetObject("cmd_grabar.Image"), System.Drawing.Image)
        Me.cmd_grabar.Location = New System.Drawing.Point(68, 354)
        Me.cmd_grabar.Name = "cmd_grabar"
        Me.cmd_grabar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_grabar.TabIndex = 12
        Me.ToolTip1.SetToolTip(Me.cmd_grabar, "Guardar")
        Me.cmd_grabar.UseVisualStyleBackColor = True
        '
        'cmd_nuevo
        '
        Me.cmd_nuevo.Image = CType(resources.GetObject("cmd_nuevo.Image"), System.Drawing.Image)
        Me.cmd_nuevo.Location = New System.Drawing.Point(17, 354)
        Me.cmd_nuevo.Name = "cmd_nuevo"
        Me.cmd_nuevo.Size = New System.Drawing.Size(45, 45)
        Me.cmd_nuevo.TabIndex = 11
        Me.ToolTip1.SetToolTip(Me.cmd_nuevo, "Nuevo")
        Me.cmd_nuevo.UseVisualStyleBackColor = True
        '
        'cmd_buscarImagen
        '
        Me.cmd_buscarImagen.Location = New System.Drawing.Point(322, 12)
        Me.cmd_buscarImagen.Name = "cmd_buscarImagen"
        Me.cmd_buscarImagen.Size = New System.Drawing.Size(23, 25)
        Me.cmd_buscarImagen.TabIndex = 23
        Me.cmd_buscarImagen.Text = "..."
        Me.cmd_buscarImagen.UseVisualStyleBackColor = True
        '
        'Label14
        '
        Me.Label14.AutoSize = True
        Me.Label14.Location = New System.Drawing.Point(274, 22)
        Me.Label14.Name = "Label14"
        Me.Label14.Size = New System.Drawing.Size(42, 13)
        Me.Label14.TabIndex = 21
        Me.Label14.Text = "Imagen"
        '
        'OpenFileDialog1
        '
        Me.OpenFileDialog1.FileName = "OpenFileDialog1"
        '
        'PictureBox1
        '
        Me.PictureBox1.BackColor = System.Drawing.SystemColors.AppWorkspace
        Me.PictureBox1.InitialImage = Global.MenuProyecto.My.Resources.Resources.noimagen
        Me.PictureBox1.Location = New System.Drawing.Point(274, 38)
        Me.PictureBox1.Name = "PictureBox1"
        Me.PictureBox1.Size = New System.Drawing.Size(216, 280)
        Me.PictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage
        Me.PictureBox1.TabIndex = 22
        Me.PictureBox1.TabStop = False
        '
        'frm_producto
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(922, 430)
        Me.Controls.Add(Me.cmd_buscarImagen)
        Me.Controls.Add(Me.PictureBox1)
        Me.Controls.Add(Me.Label14)
        Me.Controls.Add(Me.GroupBox1)
        Me.Controls.Add(Me.Label12)
        Me.Controls.Add(Me.grid_producto)
        Me.Controls.Add(Me.cmd_salir)
        Me.Controls.Add(Me.cmd_modificar)
        Me.Controls.Add(Me.cmd_cancelar)
        Me.Controls.Add(Me.cmd_eliminar)
        Me.Controls.Add(Me.cmd_grabar)
        Me.Controls.Add(Me.cmd_nuevo)
        Me.Controls.Add(Me.Label11)
        Me.Controls.Add(Me.Label10)
        Me.Controls.Add(Me.Label9)
        Me.Controls.Add(Me.Label8)
        Me.Controls.Add(Me.Label7)
        Me.Controls.Add(Me.Label6)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.txt_garantia)
        Me.Controls.Add(Me.txt_cantidad)
        Me.Controls.Add(Me.txt_alto)
        Me.Controls.Add(Me.txt_ancho)
        Me.Controls.Add(Me.txt_largo)
        Me.Controls.Add(Me.txt_peso)
        Me.Controls.Add(Me.txt_material_principal)
        Me.Controls.Add(Me.txt_color)
        Me.Controls.Add(Me.txt_descripcion)
        Me.Controls.Add(Me.txt_precio)
        Me.Controls.Add(Me.txt_nombre)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D
        Me.MaximizeBox = False
        Me.Name = "frm_producto"
        Me.Text = "Producto"
        CType(Me.grid_producto, System.ComponentModel.ISupportInitialize).EndInit()
        Me.GroupBox1.ResumeLayout(False)
        Me.GroupBox1.PerformLayout()
        CType(Me.PictureBox1, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents txt_nombre As System.Windows.Forms.TextBox
    Friend WithEvents txt_precio As System.Windows.Forms.TextBox
    Friend WithEvents txt_descripcion As System.Windows.Forms.TextBox
    Friend WithEvents txt_color As System.Windows.Forms.TextBox
    Friend WithEvents txt_material_principal As System.Windows.Forms.TextBox
    Friend WithEvents txt_peso As System.Windows.Forms.TextBox
    Friend WithEvents txt_largo As System.Windows.Forms.TextBox
    Friend WithEvents txt_ancho As System.Windows.Forms.TextBox
    Friend WithEvents txt_alto As System.Windows.Forms.TextBox
    Friend WithEvents txt_cantidad As System.Windows.Forms.TextBox
    Friend WithEvents txt_garantia As System.Windows.Forms.TextBox
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents Label7 As System.Windows.Forms.Label
    Friend WithEvents Label8 As System.Windows.Forms.Label
    Friend WithEvents Label9 As System.Windows.Forms.Label
    Friend WithEvents Label10 As System.Windows.Forms.Label
    Friend WithEvents Label11 As System.Windows.Forms.Label
    Friend WithEvents cmd_nuevo As System.Windows.Forms.Button
    Friend WithEvents cmd_grabar As System.Windows.Forms.Button
    Friend WithEvents cmd_eliminar As System.Windows.Forms.Button
    Friend WithEvents cmd_salir As System.Windows.Forms.Button
    Friend WithEvents grid_producto As System.Windows.Forms.DataGridView
    Friend WithEvents Label12 As System.Windows.Forms.Label
    Friend WithEvents Id_Producto As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Nombre As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents precio As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Descripcion As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Cant_Stock As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents cmd_modificar As System.Windows.Forms.Button
    Friend WithEvents cmd_cancelar As System.Windows.Forms.Button
    Friend WithEvents txt_buscar As System.Windows.Forms.TextBox
    Friend WithEvents cmd_limpiar As System.Windows.Forms.Button
    Friend WithEvents Label13 As System.Windows.Forms.Label
    Friend WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
    Friend WithEvents cmd_buscarImagen As System.Windows.Forms.Button
    Friend WithEvents PictureBox1 As System.Windows.Forms.PictureBox
    Friend WithEvents Label14 As System.Windows.Forms.Label
    Friend WithEvents OpenFileDialog1 As System.Windows.Forms.OpenFileDialog
End Class
