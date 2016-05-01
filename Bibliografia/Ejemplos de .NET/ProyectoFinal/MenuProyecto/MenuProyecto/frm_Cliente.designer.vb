<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frm_Cliente
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
        Me.lbl_nombre = New System.Windows.Forms.Label()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Label7 = New System.Windows.Forms.Label()
        Me.Label8 = New System.Windows.Forms.Label()
        Me.Label10 = New System.Windows.Forms.Label()
        Me.Label9 = New System.Windows.Forms.Label()
        Me.Label11 = New System.Windows.Forms.Label()
        Me.Label12 = New System.Windows.Forms.Label()
        Me.Label13 = New System.Windows.Forms.Label()
        Me.txt_nombre = New System.Windows.Forms.TextBox()
        Me.txt_apellido = New System.Windows.Forms.TextBox()
        Me.txt_numeroDocumento = New System.Windows.Forms.TextBox()
        Me.cmb_tipoDocumento = New System.Windows.Forms.ComboBox()
        Me.GroupBox1 = New System.Windows.Forms.GroupBox()
        Me.opt_femenino = New System.Windows.Forms.RadioButton()
        Me.opt_masculino = New System.Windows.Forms.RadioButton()
        Me.txt_calle = New System.Windows.Forms.TextBox()
        Me.txt_numero = New System.Windows.Forms.TextBox()
        Me.cmb_provincia = New System.Windows.Forms.ComboBox()
        Me.cmb_localidad = New System.Windows.Forms.ComboBox()
        Me.cmb_barrio = New System.Windows.Forms.ComboBox()
        Me.txt_email = New System.Windows.Forms.TextBox()
        Me.txt_telefono = New System.Windows.Forms.TextBox()
        Me.grid_clientes = New System.Windows.Forms.DataGridView()
        Me.Nombre = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Nro_Doc = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Apellido = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Nombres = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.txt_fechaNacimiento = New System.Windows.Forms.MaskedTextBox()
        Me.GroupBox2 = New System.Windows.Forms.GroupBox()
        Me.txt_buscar = New System.Windows.Forms.TextBox()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.gbox3 = New System.Windows.Forms.GroupBox()
        Me.cmd_limpiar = New System.Windows.Forms.Button()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.cmd_eliminar = New System.Windows.Forms.Button()
        Me.cmd_salir = New System.Windows.Forms.Button()
        Me.cmd_modificar = New System.Windows.Forms.Button()
        Me.cmd_cancelar = New System.Windows.Forms.Button()
        Me.cmd_guardar = New System.Windows.Forms.Button()
        Me.cmd_nuevo = New System.Windows.Forms.Button()
        Me.cmd_agregarlocalidad = New System.Windows.Forms.Button()
        Me.cmd_agregarbarrio = New System.Windows.Forms.Button()
        Me.GroupBox1.SuspendLayout()
        CType(Me.grid_clientes, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.GroupBox2.SuspendLayout()
        Me.gbox3.SuspendLayout()
        Me.SuspendLayout()
        '
        'lbl_nombre
        '
        Me.lbl_nombre.AutoSize = True
        Me.lbl_nombre.Location = New System.Drawing.Point(101, 107)
        Me.lbl_nombre.Name = "lbl_nombre"
        Me.lbl_nombre.Size = New System.Drawing.Size(44, 13)
        Me.lbl_nombre.TabIndex = 0
        Me.lbl_nombre.Text = "Nombre"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(100, 142)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(44, 13)
        Me.Label1.TabIndex = 0
        Me.Label1.Text = "Apellido"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(44, 40)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(101, 13)
        Me.Label2.TabIndex = 0
        Me.Label2.Text = "Tipo de Documento"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(28, 77)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(117, 13)
        Me.Label3.TabIndex = 0
        Me.Label3.Text = "Número de Documento"
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(37, 170)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(108, 13)
        Me.Label5.TabIndex = 0
        Me.Label5.Text = "Fecha de Nacimiento"
        '
        'Label7
        '
        Me.Label7.AutoSize = True
        Me.Label7.Location = New System.Drawing.Point(58, 12)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(30, 13)
        Me.Label7.TabIndex = 0
        Me.Label7.Text = "Calle"
        '
        'Label8
        '
        Me.Label8.AutoSize = True
        Me.Label8.Location = New System.Drawing.Point(44, 41)
        Me.Label8.Name = "Label8"
        Me.Label8.Size = New System.Drawing.Size(44, 13)
        Me.Label8.TabIndex = 0
        Me.Label8.Text = "Numero"
        '
        'Label10
        '
        Me.Label10.AutoSize = True
        Me.Label10.Location = New System.Drawing.Point(37, 73)
        Me.Label10.Name = "Label10"
        Me.Label10.Size = New System.Drawing.Size(51, 13)
        Me.Label10.TabIndex = 0
        Me.Label10.Text = "Provincia"
        '
        'Label9
        '
        Me.Label9.AutoSize = True
        Me.Label9.Location = New System.Drawing.Point(35, 109)
        Me.Label9.Name = "Label9"
        Me.Label9.Size = New System.Drawing.Size(53, 13)
        Me.Label9.TabIndex = 0
        Me.Label9.Text = "Localidad"
        '
        'Label11
        '
        Me.Label11.AutoSize = True
        Me.Label11.Location = New System.Drawing.Point(50, 142)
        Me.Label11.Name = "Label11"
        Me.Label11.Size = New System.Drawing.Size(34, 13)
        Me.Label11.TabIndex = 0
        Me.Label11.Text = "Barrio"
        '
        'Label12
        '
        Me.Label12.AutoSize = True
        Me.Label12.Location = New System.Drawing.Point(109, 282)
        Me.Label12.Name = "Label12"
        Me.Label12.Size = New System.Drawing.Size(36, 13)
        Me.Label12.TabIndex = 0
        Me.Label12.Text = "E-Mail"
        '
        'Label13
        '
        Me.Label13.AutoSize = True
        Me.Label13.Location = New System.Drawing.Point(96, 317)
        Me.Label13.Name = "Label13"
        Me.Label13.Size = New System.Drawing.Size(49, 13)
        Me.Label13.TabIndex = 0
        Me.Label13.Text = "Teléfono"
        '
        'txt_nombre
        '
        Me.txt_nombre.Location = New System.Drawing.Point(150, 104)
        Me.txt_nombre.Name = "txt_nombre"
        Me.txt_nombre.Size = New System.Drawing.Size(100, 20)
        Me.txt_nombre.TabIndex = 2
        '
        'txt_apellido
        '
        Me.txt_apellido.Location = New System.Drawing.Point(150, 135)
        Me.txt_apellido.Name = "txt_apellido"
        Me.txt_apellido.Size = New System.Drawing.Size(100, 20)
        Me.txt_apellido.TabIndex = 3
        '
        'txt_numeroDocumento
        '
        Me.txt_numeroDocumento.Location = New System.Drawing.Point(151, 70)
        Me.txt_numeroDocumento.Name = "txt_numeroDocumento"
        Me.txt_numeroDocumento.Size = New System.Drawing.Size(81, 20)
        Me.txt_numeroDocumento.TabIndex = 1
        '
        'cmb_tipoDocumento
        '
        Me.cmb_tipoDocumento.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_tipoDocumento.FormattingEnabled = True
        Me.cmb_tipoDocumento.Location = New System.Drawing.Point(151, 36)
        Me.cmb_tipoDocumento.Name = "cmb_tipoDocumento"
        Me.cmb_tipoDocumento.Size = New System.Drawing.Size(100, 21)
        Me.cmb_tipoDocumento.TabIndex = 0
        '
        'GroupBox1
        '
        Me.GroupBox1.Controls.Add(Me.opt_femenino)
        Me.GroupBox1.Controls.Add(Me.opt_masculino)
        Me.GroupBox1.Location = New System.Drawing.Point(104, 198)
        Me.GroupBox1.Name = "GroupBox1"
        Me.GroupBox1.Size = New System.Drawing.Size(164, 62)
        Me.GroupBox1.TabIndex = 5
        Me.GroupBox1.TabStop = False
        Me.GroupBox1.Text = "Sexo"
        '
        'opt_femenino
        '
        Me.opt_femenino.AutoSize = True
        Me.opt_femenino.Location = New System.Drawing.Point(47, 41)
        Me.opt_femenino.Name = "opt_femenino"
        Me.opt_femenino.Size = New System.Drawing.Size(71, 17)
        Me.opt_femenino.TabIndex = 1
        Me.opt_femenino.Text = "Femenino"
        Me.opt_femenino.UseVisualStyleBackColor = True
        '
        'opt_masculino
        '
        Me.opt_masculino.AutoSize = True
        Me.opt_masculino.Location = New System.Drawing.Point(47, 19)
        Me.opt_masculino.Name = "opt_masculino"
        Me.opt_masculino.Size = New System.Drawing.Size(73, 17)
        Me.opt_masculino.TabIndex = 0
        Me.opt_masculino.Text = "Masculino"
        Me.opt_masculino.UseVisualStyleBackColor = True
        '
        'txt_calle
        '
        Me.txt_calle.Location = New System.Drawing.Point(94, 5)
        Me.txt_calle.Name = "txt_calle"
        Me.txt_calle.Size = New System.Drawing.Size(100, 20)
        Me.txt_calle.TabIndex = 0
        '
        'txt_numero
        '
        Me.txt_numero.Location = New System.Drawing.Point(94, 34)
        Me.txt_numero.Name = "txt_numero"
        Me.txt_numero.Size = New System.Drawing.Size(100, 20)
        Me.txt_numero.TabIndex = 1
        '
        'cmb_provincia
        '
        Me.cmb_provincia.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_provincia.FormattingEnabled = True
        Me.cmb_provincia.Location = New System.Drawing.Point(94, 65)
        Me.cmb_provincia.Name = "cmb_provincia"
        Me.cmb_provincia.Size = New System.Drawing.Size(100, 21)
        Me.cmb_provincia.TabIndex = 2
        '
        'cmb_localidad
        '
        Me.cmb_localidad.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_localidad.FormattingEnabled = True
        Me.cmb_localidad.Location = New System.Drawing.Point(94, 101)
        Me.cmb_localidad.Name = "cmb_localidad"
        Me.cmb_localidad.Size = New System.Drawing.Size(100, 21)
        Me.cmb_localidad.TabIndex = 3
        '
        'cmb_barrio
        '
        Me.cmb_barrio.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_barrio.FormattingEnabled = True
        Me.cmb_barrio.Location = New System.Drawing.Point(94, 134)
        Me.cmb_barrio.Name = "cmb_barrio"
        Me.cmb_barrio.Size = New System.Drawing.Size(100, 21)
        Me.cmb_barrio.TabIndex = 5
        '
        'txt_email
        '
        Me.txt_email.Location = New System.Drawing.Point(151, 275)
        Me.txt_email.Name = "txt_email"
        Me.txt_email.Size = New System.Drawing.Size(100, 20)
        Me.txt_email.TabIndex = 6
        '
        'txt_telefono
        '
        Me.txt_telefono.Location = New System.Drawing.Point(151, 310)
        Me.txt_telefono.Name = "txt_telefono"
        Me.txt_telefono.Size = New System.Drawing.Size(100, 20)
        Me.txt_telefono.TabIndex = 7
        '
        'grid_clientes
        '
        Me.grid_clientes.AllowUserToAddRows = False
        Me.grid_clientes.AllowUserToDeleteRows = False
        Me.grid_clientes.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.grid_clientes.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.Nombre, Me.Nro_Doc, Me.Apellido, Me.Nombres})
        Me.grid_clientes.Location = New System.Drawing.Point(336, 36)
        Me.grid_clientes.Name = "grid_clientes"
        Me.grid_clientes.ReadOnly = True
        Me.grid_clientes.Size = New System.Drawing.Size(437, 457)
        Me.grid_clientes.TabIndex = 17
        '
        'Nombre
        '
        Me.Nombre.HeaderText = "Tipo Doc"
        Me.Nombre.Name = "Nombre"
        Me.Nombre.ReadOnly = True
        Me.Nombre.Width = 75
        '
        'Nro_Doc
        '
        Me.Nro_Doc.HeaderText = "Nro Doc"
        Me.Nro_Doc.Name = "Nro_Doc"
        Me.Nro_Doc.ReadOnly = True
        Me.Nro_Doc.Width = 75
        '
        'Apellido
        '
        Me.Apellido.HeaderText = "Apellido"
        Me.Apellido.Name = "Apellido"
        Me.Apellido.ReadOnly = True
        Me.Apellido.Width = 120
        '
        'Nombres
        '
        Me.Nombres.HeaderText = "Nombres"
        Me.Nombres.Name = "Nombres"
        Me.Nombres.ReadOnly = True
        Me.Nombres.Width = 120
        '
        'txt_fechaNacimiento
        '
        Me.txt_fechaNacimiento.Location = New System.Drawing.Point(152, 162)
        Me.txt_fechaNacimiento.Mask = "00/00/0000"
        Me.txt_fechaNacimiento.Name = "txt_fechaNacimiento"
        Me.txt_fechaNacimiento.Size = New System.Drawing.Size(70, 20)
        Me.txt_fechaNacimiento.TabIndex = 4
        Me.txt_fechaNacimiento.ValidatingType = GetType(Date)
        '
        'GroupBox2
        '
        Me.GroupBox2.Controls.Add(Me.cmd_agregarbarrio)
        Me.GroupBox2.Controls.Add(Me.cmd_agregarlocalidad)
        Me.GroupBox2.Controls.Add(Me.cmb_localidad)
        Me.GroupBox2.Controls.Add(Me.cmb_provincia)
        Me.GroupBox2.Controls.Add(Me.txt_numero)
        Me.GroupBox2.Controls.Add(Me.txt_calle)
        Me.GroupBox2.Controls.Add(Me.cmb_barrio)
        Me.GroupBox2.Controls.Add(Me.Label9)
        Me.GroupBox2.Controls.Add(Me.Label10)
        Me.GroupBox2.Controls.Add(Me.Label8)
        Me.GroupBox2.Controls.Add(Me.Label7)
        Me.GroupBox2.Controls.Add(Me.Label11)
        Me.GroupBox2.Location = New System.Drawing.Point(56, 349)
        Me.GroupBox2.Name = "GroupBox2"
        Me.GroupBox2.Size = New System.Drawing.Size(255, 164)
        Me.GroupBox2.TabIndex = 8
        Me.GroupBox2.TabStop = False
        Me.GroupBox2.Text = "Dirección"
        '
        'txt_buscar
        '
        Me.txt_buscar.Location = New System.Drawing.Point(66, 24)
        Me.txt_buscar.Name = "txt_buscar"
        Me.txt_buscar.Size = New System.Drawing.Size(225, 20)
        Me.txt_buscar.TabIndex = 0
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(16, 27)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(44, 13)
        Me.Label4.TabIndex = 0
        Me.Label4.Text = "Apellido"
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.Font = New System.Drawing.Font("Microsoft Sans Serif", 9.75!, CType((System.Drawing.FontStyle.Bold Or System.Drawing.FontStyle.Underline), System.Drawing.FontStyle), System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label6.Location = New System.Drawing.Point(314, 17)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(64, 16)
        Me.Label6.TabIndex = 18
        Me.Label6.Text = "Clientes"
        '
        'gbox3
        '
        Me.gbox3.Controls.Add(Me.cmd_limpiar)
        Me.gbox3.Controls.Add(Me.txt_buscar)
        Me.gbox3.Controls.Add(Me.Label4)
        Me.gbox3.Location = New System.Drawing.Point(357, 499)
        Me.gbox3.Name = "gbox3"
        Me.gbox3.Size = New System.Drawing.Size(331, 56)
        Me.gbox3.TabIndex = 14
        Me.gbox3.TabStop = False
        Me.gbox3.Text = "Buscador"
        '
        'cmd_limpiar
        '
        Me.cmd_limpiar.Image = Global.MenuProyecto.My.Resources.Resources.icono_x
        Me.cmd_limpiar.Location = New System.Drawing.Point(297, 23)
        Me.cmd_limpiar.Name = "cmd_limpiar"
        Me.cmd_limpiar.Size = New System.Drawing.Size(20, 20)
        Me.cmd_limpiar.TabIndex = 1
        Me.ToolTip1.SetToolTip(Me.cmd_limpiar, "Buscar")
        Me.cmd_limpiar.UseVisualStyleBackColor = True
        '
        'cmd_eliminar
        '
        Me.cmd_eliminar.Image = Global.MenuProyecto.My.Resources.Resources.Icono_eliminar2
        Me.cmd_eliminar.Location = New System.Drawing.Point(210, 537)
        Me.cmd_eliminar.Name = "cmd_eliminar"
        Me.cmd_eliminar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_eliminar.TabIndex = 12
        Me.ToolTip1.SetToolTip(Me.cmd_eliminar, "Eliminar")
        Me.cmd_eliminar.UseVisualStyleBackColor = True
        '
        'cmd_salir
        '
        Me.cmd_salir.Image = Global.MenuProyecto.My.Resources.Resources.puerta
        Me.cmd_salir.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft
        Me.cmd_salir.Location = New System.Drawing.Point(727, 537)
        Me.cmd_salir.Name = "cmd_salir"
        Me.cmd_salir.Size = New System.Drawing.Size(45, 45)
        Me.cmd_salir.TabIndex = 15
        Me.ToolTip1.SetToolTip(Me.cmd_salir, "Salir")
        Me.cmd_salir.UseVisualStyleBackColor = True
        '
        'cmd_modificar
        '
        Me.cmd_modificar.Image = Global.MenuProyecto.My.Resources.Resources.Icono_modificar
        Me.cmd_modificar.Location = New System.Drawing.Point(159, 537)
        Me.cmd_modificar.Name = "cmd_modificar"
        Me.cmd_modificar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_modificar.TabIndex = 11
        Me.ToolTip1.SetToolTip(Me.cmd_modificar, "Modificar")
        Me.cmd_modificar.UseVisualStyleBackColor = True
        '
        'cmd_cancelar
        '
        Me.cmd_cancelar.Image = Global.MenuProyecto.My.Resources.Resources.splashcancelar
        Me.cmd_cancelar.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft
        Me.cmd_cancelar.Location = New System.Drawing.Point(261, 537)
        Me.cmd_cancelar.Name = "cmd_cancelar"
        Me.cmd_cancelar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_cancelar.TabIndex = 13
        Me.ToolTip1.SetToolTip(Me.cmd_cancelar, "Cancelar")
        Me.cmd_cancelar.UseVisualStyleBackColor = True
        '
        'cmd_guardar
        '
        Me.cmd_guardar.Image = Global.MenuProyecto.My.Resources.Resources.Grabar2
        Me.cmd_guardar.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft
        Me.cmd_guardar.Location = New System.Drawing.Point(107, 537)
        Me.cmd_guardar.Name = "cmd_guardar"
        Me.cmd_guardar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_guardar.TabIndex = 10
        Me.ToolTip1.SetToolTip(Me.cmd_guardar, "Guardar")
        Me.cmd_guardar.UseVisualStyleBackColor = True
        '
        'cmd_nuevo
        '
        Me.cmd_nuevo.Image = Global.MenuProyecto.My.Resources.Resources.Nuevo2
        Me.cmd_nuevo.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft
        Me.cmd_nuevo.Location = New System.Drawing.Point(56, 537)
        Me.cmd_nuevo.Name = "cmd_nuevo"
        Me.cmd_nuevo.Size = New System.Drawing.Size(45, 45)
        Me.cmd_nuevo.TabIndex = 9
        Me.ToolTip1.SetToolTip(Me.cmd_nuevo, "Nuevo")
        Me.cmd_nuevo.UseVisualStyleBackColor = True
        '
        'cmd_agregarlocalidad
        '
        Me.cmd_agregarlocalidad.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.cmd_agregarlocalidad.Location = New System.Drawing.Point(200, 100)
        Me.cmd_agregarlocalidad.Name = "cmd_agregarlocalidad"
        Me.cmd_agregarlocalidad.Size = New System.Drawing.Size(21, 21)
        Me.cmd_agregarlocalidad.TabIndex = 4
        Me.cmd_agregarlocalidad.Text = "+"
        Me.cmd_agregarlocalidad.UseVisualStyleBackColor = True
        '
        'cmd_agregarbarrio
        '
        Me.cmd_agregarbarrio.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.cmd_agregarbarrio.Location = New System.Drawing.Point(200, 133)
        Me.cmd_agregarbarrio.Name = "cmd_agregarbarrio"
        Me.cmd_agregarbarrio.Size = New System.Drawing.Size(21, 21)
        Me.cmd_agregarbarrio.TabIndex = 6
        Me.cmd_agregarbarrio.Text = "+"
        Me.cmd_agregarbarrio.UseVisualStyleBackColor = True
        '
        'frm_Cliente
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(784, 594)
        Me.Controls.Add(Me.gbox3)
        Me.Controls.Add(Me.Label6)
        Me.Controls.Add(Me.cmd_eliminar)
        Me.Controls.Add(Me.cmd_salir)
        Me.Controls.Add(Me.cmd_modificar)
        Me.Controls.Add(Me.cmd_cancelar)
        Me.Controls.Add(Me.cmd_guardar)
        Me.Controls.Add(Me.cmd_nuevo)
        Me.Controls.Add(Me.GroupBox2)
        Me.Controls.Add(Me.txt_fechaNacimiento)
        Me.Controls.Add(Me.grid_clientes)
        Me.Controls.Add(Me.GroupBox1)
        Me.Controls.Add(Me.cmb_tipoDocumento)
        Me.Controls.Add(Me.txt_telefono)
        Me.Controls.Add(Me.txt_email)
        Me.Controls.Add(Me.txt_numeroDocumento)
        Me.Controls.Add(Me.txt_apellido)
        Me.Controls.Add(Me.txt_nombre)
        Me.Controls.Add(Me.Label13)
        Me.Controls.Add(Me.Label12)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.lbl_nombre)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D
        Me.MaximizeBox = False
        Me.Name = "frm_Cliente"
        Me.Text = "Cliente"
        Me.GroupBox1.ResumeLayout(False)
        Me.GroupBox1.PerformLayout()
        CType(Me.grid_clientes, System.ComponentModel.ISupportInitialize).EndInit()
        Me.GroupBox2.ResumeLayout(False)
        Me.GroupBox2.PerformLayout()
        Me.gbox3.ResumeLayout(False)
        Me.gbox3.PerformLayout()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents lbl_nombre As System.Windows.Forms.Label
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents Label7 As System.Windows.Forms.Label
    Friend WithEvents Label8 As System.Windows.Forms.Label
    Friend WithEvents Label10 As System.Windows.Forms.Label
    Friend WithEvents Label9 As System.Windows.Forms.Label
    Friend WithEvents Label11 As System.Windows.Forms.Label
    Friend WithEvents Label12 As System.Windows.Forms.Label
    Friend WithEvents Label13 As System.Windows.Forms.Label
    Friend WithEvents txt_nombre As System.Windows.Forms.TextBox
    Friend WithEvents txt_apellido As System.Windows.Forms.TextBox
    Friend WithEvents txt_numeroDocumento As System.Windows.Forms.TextBox
    Friend WithEvents cmb_tipoDocumento As System.Windows.Forms.ComboBox
    Friend WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents opt_femenino As System.Windows.Forms.RadioButton
    Friend WithEvents opt_masculino As System.Windows.Forms.RadioButton
    Friend WithEvents txt_calle As System.Windows.Forms.TextBox
    Friend WithEvents txt_numero As System.Windows.Forms.TextBox
    Friend WithEvents cmb_provincia As System.Windows.Forms.ComboBox
    Friend WithEvents cmb_localidad As System.Windows.Forms.ComboBox
    Friend WithEvents cmb_barrio As System.Windows.Forms.ComboBox
    Friend WithEvents txt_email As System.Windows.Forms.TextBox
    Friend WithEvents txt_telefono As System.Windows.Forms.TextBox
    Friend WithEvents grid_clientes As System.Windows.Forms.DataGridView
    Friend WithEvents txt_fechaNacimiento As System.Windows.Forms.MaskedTextBox
    Friend WithEvents GroupBox2 As System.Windows.Forms.GroupBox
    Friend WithEvents cmd_nuevo As System.Windows.Forms.Button
    Friend WithEvents cmd_guardar As System.Windows.Forms.Button
    Friend WithEvents cmd_cancelar As System.Windows.Forms.Button
    Friend WithEvents cmd_salir As System.Windows.Forms.Button
    Friend WithEvents cmd_limpiar As System.Windows.Forms.Button
    Friend WithEvents cmd_modificar As System.Windows.Forms.Button
    Friend WithEvents txt_buscar As System.Windows.Forms.TextBox
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents cmd_eliminar As System.Windows.Forms.Button
    Friend WithEvents Nombre As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Nro_Doc As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Apellido As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Nombres As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents gbox3 As System.Windows.Forms.GroupBox
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
    Friend WithEvents cmd_agregarbarrio As System.Windows.Forms.Button
    Friend WithEvents cmd_agregarlocalidad As System.Windows.Forms.Button
End Class
