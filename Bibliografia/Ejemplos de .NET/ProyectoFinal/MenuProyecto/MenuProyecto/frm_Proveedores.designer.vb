<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frm_proveedores
    Inherits System.Windows.Forms.Form

    'Form reemplaza a Dispose para limpiar la lista de componentes.
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

    'Requerido por el Diseñador de Windows Forms
    Private components As System.ComponentModel.IContainer

    'NOTA: el Diseñador de Windows Forms necesita el siguiente procedimiento
    'Se puede modificar usando el Diseñador de Windows Forms.  
    'No lo modifique con el editor de código.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.txt_razon_social = New System.Windows.Forms.TextBox()
        Me.txt_telefono = New System.Windows.Forms.TextBox()
        Me.txt_apellido = New System.Windows.Forms.TextBox()
        Me.txt_nombre = New System.Windows.Forms.TextBox()
        Me.grid_proveedor = New System.Windows.Forms.DataGridView()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.Label7 = New System.Windows.Forms.Label()
        Me.txt_calle = New System.Windows.Forms.TextBox()
        Me.txt_numero = New System.Windows.Forms.TextBox()
        Me.cmb_Provincia = New System.Windows.Forms.ComboBox()
        Me.GroupBox1 = New System.Windows.Forms.GroupBox()
        Me.cmd_agregarbarrio = New System.Windows.Forms.Button()
        Me.cmb_barrio = New System.Windows.Forms.ComboBox()
        Me.cmd_agregarlocalidad = New System.Windows.Forms.Button()
        Me.Label9 = New System.Windows.Forms.Label()
        Me.cmb_localidad = New System.Windows.Forms.ComboBox()
        Me.Label8 = New System.Windows.Forms.Label()
        Me.Label10 = New System.Windows.Forms.Label()
        Me.Label11 = New System.Windows.Forms.Label()
        Me.txt_buscar = New System.Windows.Forms.TextBox()
        Me.cmd_salir = New System.Windows.Forms.Button()
        Me.cmd_cancelar = New System.Windows.Forms.Button()
        Me.cmd_eliminar = New System.Windows.Forms.Button()
        Me.cmd_modificar = New System.Windows.Forms.Button()
        Me.cmd_grabar = New System.Windows.Forms.Button()
        Me.cmd_limpiar = New System.Windows.Forms.Button()
        Me.cmd_nuevo = New System.Windows.Forms.Button()
        Me.gbox2 = New System.Windows.Forms.GroupBox()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.txt_busqueda = New System.Windows.Forms.TextBox()
        Me.Id_Proveedor = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Razon_Social = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Telefono_Contacto = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Nombre_Contacto = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Apellido_Contacto = New System.Windows.Forms.DataGridViewTextBoxColumn()
        CType(Me.grid_proveedor, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.GroupBox1.SuspendLayout()
        Me.gbox2.SuspendLayout()
        Me.SuspendLayout()
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(57, 43)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(70, 13)
        Me.Label1.TabIndex = 0
        Me.Label1.Text = "Razón Social"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(17, 74)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(110, 13)
        Me.Label2.TabIndex = 1
        Me.Label2.Text = "Teléfono de Contacto"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(22, 111)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(105, 13)
        Me.Label3.TabIndex = 2
        Me.Label3.Text = "Nombre de Contacto"
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(22, 151)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(105, 13)
        Me.Label4.TabIndex = 3
        Me.Label4.Text = "Apellido de Contacto"
        '
        'txt_razon_social
        '
        Me.txt_razon_social.Location = New System.Drawing.Point(133, 40)
        Me.txt_razon_social.Name = "txt_razon_social"
        Me.txt_razon_social.Size = New System.Drawing.Size(141, 20)
        Me.txt_razon_social.TabIndex = 0
        '
        'txt_telefono
        '
        Me.txt_telefono.Location = New System.Drawing.Point(133, 71)
        Me.txt_telefono.Name = "txt_telefono"
        Me.txt_telefono.Size = New System.Drawing.Size(141, 20)
        Me.txt_telefono.TabIndex = 1
        '
        'txt_apellido
        '
        Me.txt_apellido.Location = New System.Drawing.Point(133, 148)
        Me.txt_apellido.Name = "txt_apellido"
        Me.txt_apellido.Size = New System.Drawing.Size(141, 20)
        Me.txt_apellido.TabIndex = 3
        '
        'txt_nombre
        '
        Me.txt_nombre.Location = New System.Drawing.Point(133, 108)
        Me.txt_nombre.Name = "txt_nombre"
        Me.txt_nombre.Size = New System.Drawing.Size(141, 20)
        Me.txt_nombre.TabIndex = 2
        '
        'grid_proveedor
        '
        Me.grid_proveedor.AllowUserToAddRows = False
        Me.grid_proveedor.AllowUserToDeleteRows = False
        Me.grid_proveedor.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.grid_proveedor.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.Id_Proveedor, Me.Razon_Social, Me.Telefono_Contacto, Me.Nombre_Contacto, Me.Apellido_Contacto})
        Me.grid_proveedor.Location = New System.Drawing.Point(327, 40)
        Me.grid_proveedor.Name = "grid_proveedor"
        Me.grid_proveedor.ReadOnly = True
        Me.grid_proveedor.Size = New System.Drawing.Size(504, 333)
        Me.grid_proveedor.TabIndex = 21
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(37, 22)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(30, 13)
        Me.Label5.TabIndex = 4
        Me.Label5.Text = "Calle"
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.Location = New System.Drawing.Point(23, 51)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(44, 13)
        Me.Label6.TabIndex = 5
        Me.Label6.Text = "Número"
        '
        'Label7
        '
        Me.Label7.AutoSize = True
        Me.Label7.Location = New System.Drawing.Point(16, 88)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(51, 13)
        Me.Label7.TabIndex = 6
        Me.Label7.Text = "Provincia"
        '
        'txt_calle
        '
        Me.txt_calle.Location = New System.Drawing.Point(73, 19)
        Me.txt_calle.Name = "txt_calle"
        Me.txt_calle.Size = New System.Drawing.Size(141, 20)
        Me.txt_calle.TabIndex = 0
        '
        'txt_numero
        '
        Me.txt_numero.Location = New System.Drawing.Point(73, 52)
        Me.txt_numero.Name = "txt_numero"
        Me.txt_numero.Size = New System.Drawing.Size(141, 20)
        Me.txt_numero.TabIndex = 1
        '
        'cmb_Provincia
        '
        Me.cmb_Provincia.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_Provincia.FormattingEnabled = True
        Me.cmb_Provincia.Location = New System.Drawing.Point(73, 85)
        Me.cmb_Provincia.Name = "cmb_Provincia"
        Me.cmb_Provincia.Size = New System.Drawing.Size(141, 21)
        Me.cmb_Provincia.TabIndex = 2
        '
        'GroupBox1
        '
        Me.GroupBox1.Controls.Add(Me.cmd_agregarbarrio)
        Me.GroupBox1.Controls.Add(Me.cmb_barrio)
        Me.GroupBox1.Controls.Add(Me.cmd_agregarlocalidad)
        Me.GroupBox1.Controls.Add(Me.Label9)
        Me.GroupBox1.Controls.Add(Me.cmb_localidad)
        Me.GroupBox1.Controls.Add(Me.Label8)
        Me.GroupBox1.Controls.Add(Me.cmb_Provincia)
        Me.GroupBox1.Controls.Add(Me.txt_numero)
        Me.GroupBox1.Controls.Add(Me.txt_calle)
        Me.GroupBox1.Controls.Add(Me.Label7)
        Me.GroupBox1.Controls.Add(Me.Label6)
        Me.GroupBox1.Controls.Add(Me.Label5)
        Me.GroupBox1.Location = New System.Drawing.Point(60, 185)
        Me.GroupBox1.Name = "GroupBox1"
        Me.GroupBox1.Size = New System.Drawing.Size(245, 188)
        Me.GroupBox1.TabIndex = 4
        Me.GroupBox1.TabStop = False
        Me.GroupBox1.Text = "Dirección"
        '
        'cmd_agregarbarrio
        '
        Me.cmd_agregarbarrio.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.cmd_agregarbarrio.Location = New System.Drawing.Point(220, 150)
        Me.cmd_agregarbarrio.Name = "cmd_agregarbarrio"
        Me.cmd_agregarbarrio.Size = New System.Drawing.Size(21, 21)
        Me.cmd_agregarbarrio.TabIndex = 6
        Me.cmd_agregarbarrio.Text = "+"
        Me.cmd_agregarbarrio.UseVisualStyleBackColor = True
        '
        'cmb_barrio
        '
        Me.cmb_barrio.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_barrio.FormattingEnabled = True
        Me.cmb_barrio.Location = New System.Drawing.Point(73, 151)
        Me.cmb_barrio.Name = "cmb_barrio"
        Me.cmb_barrio.Size = New System.Drawing.Size(141, 21)
        Me.cmb_barrio.TabIndex = 5
        '
        'cmd_agregarlocalidad
        '
        Me.cmd_agregarlocalidad.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.cmd_agregarlocalidad.Location = New System.Drawing.Point(220, 117)
        Me.cmd_agregarlocalidad.Name = "cmd_agregarlocalidad"
        Me.cmd_agregarlocalidad.Size = New System.Drawing.Size(21, 21)
        Me.cmd_agregarlocalidad.TabIndex = 4
        Me.cmd_agregarlocalidad.Text = "+"
        Me.cmd_agregarlocalidad.UseVisualStyleBackColor = True
        '
        'Label9
        '
        Me.Label9.AutoSize = True
        Me.Label9.Location = New System.Drawing.Point(33, 150)
        Me.Label9.Name = "Label9"
        Me.Label9.Size = New System.Drawing.Size(34, 13)
        Me.Label9.TabIndex = 16
        Me.Label9.Text = "Barrio"
        '
        'cmb_localidad
        '
        Me.cmb_localidad.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_localidad.FormattingEnabled = True
        Me.cmb_localidad.Location = New System.Drawing.Point(73, 118)
        Me.cmb_localidad.Name = "cmb_localidad"
        Me.cmb_localidad.Size = New System.Drawing.Size(141, 21)
        Me.cmb_localidad.TabIndex = 3
        '
        'Label8
        '
        Me.Label8.AutoSize = True
        Me.Label8.Location = New System.Drawing.Point(14, 121)
        Me.Label8.Name = "Label8"
        Me.Label8.Size = New System.Drawing.Size(53, 13)
        Me.Label8.TabIndex = 14
        Me.Label8.Text = "Localidad"
        '
        'Label10
        '
        Me.Label10.AutoSize = True
        Me.Label10.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, CType((System.Drawing.FontStyle.Bold Or System.Drawing.FontStyle.Underline), System.Drawing.FontStyle), System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label10.Location = New System.Drawing.Point(324, 21)
        Me.Label10.Name = "Label10"
        Me.Label10.Size = New System.Drawing.Size(98, 16)
        Me.Label10.TabIndex = 23
        Me.Label10.Text = "Proveedores"
        '
        'Label11
        '
        Me.Label11.AutoSize = True
        Me.Label11.Location = New System.Drawing.Point(12, 26)
        Me.Label11.Name = "Label11"
        Me.Label11.Size = New System.Drawing.Size(70, 13)
        Me.Label11.TabIndex = 24
        Me.Label11.Text = "Razón Social"
        '
        'txt_buscar
        '
        Me.txt_buscar.Location = New System.Drawing.Point(88, 23)
        Me.txt_buscar.Name = "txt_buscar"
        Me.txt_buscar.Size = New System.Drawing.Size(187, 20)
        Me.txt_buscar.TabIndex = 0
        '
        'cmd_salir
        '
        Me.cmd_salir.Image = Global.MenuProyecto.My.Resources.Resources.puerta
        Me.cmd_salir.Location = New System.Drawing.Point(758, 407)
        Me.cmd_salir.Name = "cmd_salir"
        Me.cmd_salir.Size = New System.Drawing.Size(45, 45)
        Me.cmd_salir.TabIndex = 11
        Me.ToolTip1.SetToolTip(Me.cmd_salir, "Salir")
        Me.cmd_salir.UseVisualStyleBackColor = True
        '
        'cmd_cancelar
        '
        Me.cmd_cancelar.Image = Global.MenuProyecto.My.Resources.Resources.splashcancelar
        Me.cmd_cancelar.Location = New System.Drawing.Point(239, 407)
        Me.cmd_cancelar.Name = "cmd_cancelar"
        Me.cmd_cancelar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_cancelar.TabIndex = 9
        Me.ToolTip1.SetToolTip(Me.cmd_cancelar, "Cancelar")
        Me.cmd_cancelar.UseVisualStyleBackColor = True
        '
        'cmd_eliminar
        '
        Me.cmd_eliminar.Image = Global.MenuProyecto.My.Resources.Resources.Icono_eliminar2
        Me.cmd_eliminar.Location = New System.Drawing.Point(189, 407)
        Me.cmd_eliminar.Name = "cmd_eliminar"
        Me.cmd_eliminar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_eliminar.TabIndex = 8
        Me.ToolTip1.SetToolTip(Me.cmd_eliminar, "Eliminar")
        Me.cmd_eliminar.UseVisualStyleBackColor = True
        '
        'cmd_modificar
        '
        Me.cmd_modificar.Image = Global.MenuProyecto.My.Resources.Resources.Icono_modificar
        Me.cmd_modificar.Location = New System.Drawing.Point(137, 407)
        Me.cmd_modificar.Name = "cmd_modificar"
        Me.cmd_modificar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_modificar.TabIndex = 7
        Me.ToolTip1.SetToolTip(Me.cmd_modificar, "Modificar")
        Me.cmd_modificar.UseVisualStyleBackColor = True
        '
        'cmd_grabar
        '
        Me.cmd_grabar.Image = Global.MenuProyecto.My.Resources.Resources.Grabar2
        Me.cmd_grabar.Location = New System.Drawing.Point(86, 407)
        Me.cmd_grabar.Name = "cmd_grabar"
        Me.cmd_grabar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_grabar.TabIndex = 6
        Me.ToolTip1.SetToolTip(Me.cmd_grabar, "Guardar")
        Me.cmd_grabar.UseVisualStyleBackColor = True
        '
        'cmd_limpiar
        '
        Me.cmd_limpiar.Image = Global.MenuProyecto.My.Resources.Resources.icono_x
        Me.cmd_limpiar.Location = New System.Drawing.Point(281, 22)
        Me.cmd_limpiar.Name = "cmd_limpiar"
        Me.cmd_limpiar.Size = New System.Drawing.Size(20, 20)
        Me.cmd_limpiar.TabIndex = 1
        Me.ToolTip1.SetToolTip(Me.cmd_limpiar, "Cancelar Búsqueda")
        Me.cmd_limpiar.UseVisualStyleBackColor = True
        '
        'cmd_nuevo
        '
        Me.cmd_nuevo.Image = Global.MenuProyecto.My.Resources.Resources.Nuevo2
        Me.cmd_nuevo.Location = New System.Drawing.Point(35, 407)
        Me.cmd_nuevo.Name = "cmd_nuevo"
        Me.cmd_nuevo.Size = New System.Drawing.Size(45, 45)
        Me.cmd_nuevo.TabIndex = 5
        Me.ToolTip1.SetToolTip(Me.cmd_nuevo, "Nuevo")
        Me.cmd_nuevo.UseVisualStyleBackColor = True
        '
        'gbox2
        '
        Me.gbox2.Controls.Add(Me.txt_buscar)
        Me.gbox2.Controls.Add(Me.Label11)
        Me.gbox2.Controls.Add(Me.cmd_limpiar)
        Me.gbox2.Location = New System.Drawing.Point(376, 393)
        Me.gbox2.Name = "gbox2"
        Me.gbox2.Size = New System.Drawing.Size(310, 59)
        Me.gbox2.TabIndex = 10
        Me.gbox2.TabStop = False
        Me.gbox2.Text = "Buscador"
        '
        'txt_busqueda
        '
        Me.txt_busqueda.Location = New System.Drawing.Point(88, 23)
        Me.txt_busqueda.Name = "txt_busqueda"
        Me.txt_busqueda.Size = New System.Drawing.Size(187, 20)
        Me.txt_busqueda.TabIndex = 10
        '
        'Id_Proveedor
        '
        Me.Id_Proveedor.HeaderText = "Código"
        Me.Id_Proveedor.Name = "Id_Proveedor"
        Me.Id_Proveedor.ReadOnly = True
        Me.Id_Proveedor.Width = 60
        '
        'Razon_Social
        '
        Me.Razon_Social.HeaderText = "Razón Social"
        Me.Razon_Social.Name = "Razon_Social"
        Me.Razon_Social.ReadOnly = True
        Me.Razon_Social.Width = 150
        '
        'Telefono_Contacto
        '
        Me.Telefono_Contacto.HeaderText = "Teléfono de contacto"
        Me.Telefono_Contacto.Name = "Telefono_Contacto"
        Me.Telefono_Contacto.ReadOnly = True
        '
        'Nombre_Contacto
        '
        Me.Nombre_Contacto.HeaderText = "Nombre de contacto"
        Me.Nombre_Contacto.Name = "Nombre_Contacto"
        Me.Nombre_Contacto.ReadOnly = True
        '
        'Apellido_Contacto
        '
        Me.Apellido_Contacto.HeaderText = "Apellido de Contacto"
        Me.Apellido_Contacto.Name = "Apellido_Contacto"
        Me.Apellido_Contacto.ReadOnly = True
        '
        'frm_proveedores
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(843, 464)
        Me.Controls.Add(Me.gbox2)
        Me.Controls.Add(Me.Label10)
        Me.Controls.Add(Me.GroupBox1)
        Me.Controls.Add(Me.grid_proveedor)
        Me.Controls.Add(Me.cmd_salir)
        Me.Controls.Add(Me.cmd_cancelar)
        Me.Controls.Add(Me.cmd_eliminar)
        Me.Controls.Add(Me.cmd_modificar)
        Me.Controls.Add(Me.cmd_grabar)
        Me.Controls.Add(Me.cmd_nuevo)
        Me.Controls.Add(Me.txt_nombre)
        Me.Controls.Add(Me.txt_apellido)
        Me.Controls.Add(Me.txt_telefono)
        Me.Controls.Add(Me.txt_razon_social)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.MaximizeBox = False
        Me.Name = "frm_proveedores"
        Me.Text = "Proveedores"
        CType(Me.grid_proveedor, System.ComponentModel.ISupportInitialize).EndInit()
        Me.GroupBox1.ResumeLayout(False)
        Me.GroupBox1.PerformLayout()
        Me.gbox2.ResumeLayout(False)
        Me.gbox2.PerformLayout()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents txt_razon_social As System.Windows.Forms.TextBox
    Friend WithEvents txt_telefono As System.Windows.Forms.TextBox
    Friend WithEvents txt_apellido As System.Windows.Forms.TextBox
    Friend WithEvents txt_nombre As System.Windows.Forms.TextBox
    Friend WithEvents cmd_nuevo As System.Windows.Forms.Button
    Friend WithEvents cmd_limpiar As System.Windows.Forms.Button
    Friend WithEvents cmd_grabar As System.Windows.Forms.Button
    Friend WithEvents cmd_eliminar As System.Windows.Forms.Button
    Friend WithEvents cmd_salir As System.Windows.Forms.Button
    Friend WithEvents grid_proveedor As System.Windows.Forms.DataGridView
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents Label7 As System.Windows.Forms.Label
    Friend WithEvents txt_calle As System.Windows.Forms.TextBox
    Friend WithEvents txt_numero As System.Windows.Forms.TextBox
    Friend WithEvents cmb_Provincia As System.Windows.Forms.ComboBox
    Friend WithEvents cmb_barrio As System.Windows.Forms.ComboBox
    Friend WithEvents Label9 As System.Windows.Forms.Label
    Friend WithEvents cmb_localidad As System.Windows.Forms.ComboBox
    Friend WithEvents Label8 As System.Windows.Forms.Label
    Friend WithEvents Label10 As System.Windows.Forms.Label
    Friend WithEvents cmd_modificar As System.Windows.Forms.Button
    Friend WithEvents cmd_cancelar As System.Windows.Forms.Button
    Friend WithEvents Label11 As System.Windows.Forms.Label
    Friend WithEvents txt_buscar As System.Windows.Forms.TextBox
    Friend WithEvents gbox2 As System.Windows.Forms.GroupBox
    Public WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
    Friend WithEvents txt_busqueda As System.Windows.Forms.TextBox
    Friend WithEvents cmd_agregarbarrio As System.Windows.Forms.Button
    Friend WithEvents cmd_agregarlocalidad As System.Windows.Forms.Button
    Friend WithEvents Id_Proveedor As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Razon_Social As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Telefono_Contacto As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Nombre_Contacto As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Apellido_Contacto As System.Windows.Forms.DataGridViewTextBoxColumn
End Class
