<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frm_Factura
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
        Dim DataGridViewCellStyle1 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle2 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle3 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle4 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle5 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.opt_a = New System.Windows.Forms.RadioButton()
        Me.opt_b = New System.Windows.Forms.RadioButton()
        Me.opt_c = New System.Windows.Forms.RadioButton()
        Me.GroupBox1 = New System.Windows.Forms.GroupBox()
        Me.cmb_tipodocumento = New System.Windows.Forms.ComboBox()
        Me.txt_nrodocumento = New System.Windows.Forms.TextBox()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.cmd_buscarCliente = New System.Windows.Forms.Button()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.Label7 = New System.Windows.Forms.Label()
        Me.cmb_buscarProducto = New System.Windows.Forms.Button()
        Me.Label8 = New System.Windows.Forms.Label()
        Me.Label9 = New System.Windows.Forms.Label()
        Me.Label10 = New System.Windows.Forms.Label()
        Me.cmd_agregar = New System.Windows.Forms.Button()
        Me.Panel1 = New System.Windows.Forms.Panel()
        Me.cmd_catalogo = New System.Windows.Forms.Button()
        Me.grid_detalle = New System.Windows.Forms.DataGridView()
        Me.Cantidad = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Id = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Articulo = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Precio = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Subtotal = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.txt_nombreProducto = New System.Windows.Forms.TextBox()
        Me.txt_totalProducto = New System.Windows.Forms.TextBox()
        Me.txt_precio = New System.Windows.Forms.TextBox()
        Me.txt_cantidad = New System.Windows.Forms.TextBox()
        Me.txt_codigoProducto = New System.Windows.Forms.TextBox()
        Me.cmd_salir = New System.Windows.Forms.Button()
        Me.cmd_cancelar = New System.Windows.Forms.Button()
        Me.cmd_guardar = New System.Windows.Forms.Button()
        Me.cmd_nuevo = New System.Windows.Forms.Button()
        Me.Panel2 = New System.Windows.Forms.Panel()
        Me.cmd_buscarFacturas = New System.Windows.Forms.Button()
        Me.Label11 = New System.Windows.Forms.Label()
        Me.txt_apellido = New System.Windows.Forms.TextBox()
        Me.txt_nombre = New System.Windows.Forms.TextBox()
        Me.txt_totalfactura = New System.Windows.Forms.TextBox()
        Me.txt_fechaEmision = New System.Windows.Forms.MaskedTextBox()
        Me.Label12 = New System.Windows.Forms.Label()
        Me.cmb_medio_pago = New System.Windows.Forms.ComboBox()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.GroupBox1.SuspendLayout()
        Me.Panel1.SuspendLayout()
        CType(Me.grid_detalle, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.Panel2.SuspendLayout()
        Me.SuspendLayout()
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(12, 32)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(37, 13)
        Me.Label1.TabIndex = 1
        Me.Label1.Text = "Fecha"
        '
        'opt_a
        '
        Me.opt_a.AutoSize = True
        Me.opt_a.Location = New System.Drawing.Point(25, 17)
        Me.opt_a.Name = "opt_a"
        Me.opt_a.Size = New System.Drawing.Size(32, 17)
        Me.opt_a.TabIndex = 2
        Me.opt_a.TabStop = True
        Me.opt_a.Text = "A"
        Me.opt_a.UseVisualStyleBackColor = True
        '
        'opt_b
        '
        Me.opt_b.AutoSize = True
        Me.opt_b.Location = New System.Drawing.Point(25, 40)
        Me.opt_b.Name = "opt_b"
        Me.opt_b.Size = New System.Drawing.Size(32, 17)
        Me.opt_b.TabIndex = 3
        Me.opt_b.TabStop = True
        Me.opt_b.Text = "B"
        Me.opt_b.UseVisualStyleBackColor = True
        '
        'opt_c
        '
        Me.opt_c.AutoSize = True
        Me.opt_c.Location = New System.Drawing.Point(25, 63)
        Me.opt_c.Name = "opt_c"
        Me.opt_c.Size = New System.Drawing.Size(32, 17)
        Me.opt_c.TabIndex = 4
        Me.opt_c.TabStop = True
        Me.opt_c.Text = "C"
        Me.opt_c.UseVisualStyleBackColor = True
        '
        'GroupBox1
        '
        Me.GroupBox1.Controls.Add(Me.opt_c)
        Me.GroupBox1.Controls.Add(Me.opt_b)
        Me.GroupBox1.Controls.Add(Me.opt_a)
        Me.GroupBox1.Location = New System.Drawing.Point(28, 57)
        Me.GroupBox1.Name = "GroupBox1"
        Me.GroupBox1.Size = New System.Drawing.Size(125, 85)
        Me.GroupBox1.TabIndex = 5
        Me.GroupBox1.TabStop = False
        Me.GroupBox1.Text = "Tipo de Factura"
        '
        'cmb_tipodocumento
        '
        Me.cmb_tipodocumento.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_tipodocumento.FormattingEnabled = True
        Me.cmb_tipodocumento.Location = New System.Drawing.Point(283, 25)
        Me.cmb_tipodocumento.Name = "cmb_tipodocumento"
        Me.cmb_tipodocumento.Size = New System.Drawing.Size(121, 21)
        Me.cmb_tipodocumento.TabIndex = 6
        '
        'txt_nrodocumento
        '
        Me.txt_nrodocumento.Location = New System.Drawing.Point(283, 57)
        Me.txt_nrodocumento.Name = "txt_nrodocumento"
        Me.txt_nrodocumento.Size = New System.Drawing.Size(121, 20)
        Me.txt_nrodocumento.TabIndex = 7
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(176, 33)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(101, 13)
        Me.Label2.TabIndex = 8
        Me.Label2.Text = "Tipo de Documento"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(160, 60)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(117, 13)
        Me.Label3.TabIndex = 9
        Me.Label3.Text = "Número de Documento"
        '
        'cmd_buscarCliente
        '
        Me.cmd_buscarCliente.Location = New System.Drawing.Point(410, 54)
        Me.cmd_buscarCliente.Name = "cmd_buscarCliente"
        Me.cmd_buscarCliente.Size = New System.Drawing.Size(75, 23)
        Me.cmd_buscarCliente.TabIndex = 10
        Me.cmd_buscarCliente.Text = "Buscar"
        Me.ToolTip1.SetToolTip(Me.cmd_buscarCliente, "Buscar cliente")
        Me.cmd_buscarCliente.UseVisualStyleBackColor = True
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(233, 104)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(44, 13)
        Me.Label4.TabIndex = 13
        Me.Label4.Text = "Apellido"
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(389, 104)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(44, 13)
        Me.Label5.TabIndex = 14
        Me.Label5.Text = "Nombre"
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.Location = New System.Drawing.Point(191, 21)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(44, 13)
        Me.Label6.TabIndex = 19
        Me.Label6.Text = "Nombre"
        '
        'Label7
        '
        Me.Label7.AutoSize = True
        Me.Label7.Location = New System.Drawing.Point(23, 21)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(50, 13)
        Me.Label7.TabIndex = 18
        Me.Label7.Text = "Producto"
        '
        'cmb_buscarProducto
        '
        Me.cmb_buscarProducto.Location = New System.Drawing.Point(136, 10)
        Me.cmb_buscarProducto.Name = "cmb_buscarProducto"
        Me.cmb_buscarProducto.Size = New System.Drawing.Size(49, 23)
        Me.cmb_buscarProducto.TabIndex = 20
        Me.cmb_buscarProducto.Text = "Buscar"
        Me.ToolTip1.SetToolTip(Me.cmb_buscarProducto, "Buscar Producto")
        Me.cmb_buscarProducto.UseVisualStyleBackColor = True
        '
        'Label8
        '
        Me.Label8.AutoSize = True
        Me.Label8.Location = New System.Drawing.Point(133, 65)
        Me.Label8.Name = "Label8"
        Me.Label8.Size = New System.Drawing.Size(37, 13)
        Me.Label8.TabIndex = 24
        Me.Label8.Text = "Precio"
        '
        'Label9
        '
        Me.Label9.AutoSize = True
        Me.Label9.Location = New System.Drawing.Point(24, 65)
        Me.Label9.Name = "Label9"
        Me.Label9.Size = New System.Drawing.Size(49, 13)
        Me.Label9.TabIndex = 23
        Me.Label9.Text = "Cantidad"
        '
        'Label10
        '
        Me.Label10.AutoSize = True
        Me.Label10.Location = New System.Drawing.Point(246, 65)
        Me.Label10.Name = "Label10"
        Me.Label10.Size = New System.Drawing.Size(31, 13)
        Me.Label10.TabIndex = 25
        Me.Label10.Text = "Total"
        '
        'cmd_agregar
        '
        Me.cmd_agregar.Location = New System.Drawing.Point(383, 55)
        Me.cmd_agregar.Name = "cmd_agregar"
        Me.cmd_agregar.Size = New System.Drawing.Size(58, 23)
        Me.cmd_agregar.TabIndex = 27
        Me.cmd_agregar.Text = "Agregar"
        Me.ToolTip1.SetToolTip(Me.cmd_agregar, "Agregar producto")
        Me.cmd_agregar.UseVisualStyleBackColor = True
        '
        'Panel1
        '
        Me.Panel1.Controls.Add(Me.cmd_catalogo)
        Me.Panel1.Controls.Add(Me.grid_detalle)
        Me.Panel1.Controls.Add(Me.txt_nombreProducto)
        Me.Panel1.Controls.Add(Me.txt_totalProducto)
        Me.Panel1.Controls.Add(Me.txt_precio)
        Me.Panel1.Controls.Add(Me.txt_cantidad)
        Me.Panel1.Controls.Add(Me.txt_codigoProducto)
        Me.Panel1.Controls.Add(Me.cmd_agregar)
        Me.Panel1.Controls.Add(Me.Label10)
        Me.Panel1.Controls.Add(Me.Label8)
        Me.Panel1.Controls.Add(Me.Label9)
        Me.Panel1.Controls.Add(Me.cmb_buscarProducto)
        Me.Panel1.Controls.Add(Me.Label6)
        Me.Panel1.Controls.Add(Me.Label7)
        Me.Panel1.Location = New System.Drawing.Point(19, 162)
        Me.Panel1.Name = "Panel1"
        Me.Panel1.Size = New System.Drawing.Size(560, 298)
        Me.Panel1.TabIndex = 28
        '
        'cmd_catalogo
        '
        Me.cmd_catalogo.Location = New System.Drawing.Point(455, 14)
        Me.cmd_catalogo.Name = "cmd_catalogo"
        Me.cmd_catalogo.Size = New System.Drawing.Size(86, 23)
        Me.cmd_catalogo.TabIndex = 34
        Me.cmd_catalogo.Text = "Ver Catalogo"
        Me.ToolTip1.SetToolTip(Me.cmd_catalogo, "Ver catalogo")
        Me.cmd_catalogo.UseVisualStyleBackColor = True
        '
        'grid_detalle
        '
        Me.grid_detalle.AllowUserToAddRows = False
        Me.grid_detalle.AllowUserToResizeRows = False
        DataGridViewCellStyle1.BackColor = System.Drawing.Color.FromArgb(CType(CType(255, Byte), Integer), CType(CType(224, Byte), Integer), CType(CType(192, Byte), Integer))
        Me.grid_detalle.AlternatingRowsDefaultCellStyle = DataGridViewCellStyle1
        Me.grid_detalle.CellBorderStyle = System.Windows.Forms.DataGridViewCellBorderStyle.None
        Me.grid_detalle.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.Cantidad, Me.Id, Me.Articulo, Me.Precio, Me.Subtotal})
        Me.grid_detalle.Location = New System.Drawing.Point(26, 93)
        Me.grid_detalle.Name = "grid_detalle"
        Me.grid_detalle.RowHeadersWidth = 20
        Me.grid_detalle.Size = New System.Drawing.Size(500, 190)
        Me.grid_detalle.TabIndex = 33
        '
        'Cantidad
        '
        DataGridViewCellStyle2.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleRight
        DataGridViewCellStyle2.Format = "N2"
        DataGridViewCellStyle2.NullValue = Nothing
        Me.Cantidad.DefaultCellStyle = DataGridViewCellStyle2
        Me.Cantidad.HeaderText = "Cantidad"
        Me.Cantidad.Name = "Cantidad"
        Me.Cantidad.Width = 70
        '
        'Id
        '
        DataGridViewCellStyle3.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleRight
        Me.Id.DefaultCellStyle = DataGridViewCellStyle3
        Me.Id.HeaderText = "Id"
        Me.Id.Name = "Id"
        Me.Id.Width = 50
        '
        'Articulo
        '
        Me.Articulo.HeaderText = "Artículo"
        Me.Articulo.Name = "Articulo"
        Me.Articulo.Width = 200
        '
        'Precio
        '
        DataGridViewCellStyle4.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleRight
        DataGridViewCellStyle4.Format = "N2"
        DataGridViewCellStyle4.NullValue = Nothing
        Me.Precio.DefaultCellStyle = DataGridViewCellStyle4
        Me.Precio.HeaderText = "Precio"
        Me.Precio.Name = "Precio"
        Me.Precio.Width = 62
        '
        'Subtotal
        '
        DataGridViewCellStyle5.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleRight
        DataGridViewCellStyle5.Format = "N2"
        DataGridViewCellStyle5.NullValue = Nothing
        Me.Subtotal.DefaultCellStyle = DataGridViewCellStyle5
        Me.Subtotal.HeaderText = "Subtotal"
        Me.Subtotal.Name = "Subtotal"
        Me.Subtotal.Width = 70
        '
        'txt_nombreProducto
        '
        Me.txt_nombreProducto.Enabled = False
        Me.txt_nombreProducto.Location = New System.Drawing.Point(249, 14)
        Me.txt_nombreProducto.Name = "txt_nombreProducto"
        Me.txt_nombreProducto.Size = New System.Drawing.Size(162, 20)
        Me.txt_nombreProducto.TabIndex = 32
        '
        'txt_totalProducto
        '
        Me.txt_totalProducto.Enabled = False
        Me.txt_totalProducto.Location = New System.Drawing.Point(283, 57)
        Me.txt_totalProducto.Name = "txt_totalProducto"
        Me.txt_totalProducto.Size = New System.Drawing.Size(46, 20)
        Me.txt_totalProducto.TabIndex = 31
        '
        'txt_precio
        '
        Me.txt_precio.Enabled = False
        Me.txt_precio.Location = New System.Drawing.Point(176, 57)
        Me.txt_precio.Name = "txt_precio"
        Me.txt_precio.Size = New System.Drawing.Size(46, 20)
        Me.txt_precio.TabIndex = 30
        '
        'txt_cantidad
        '
        Me.txt_cantidad.Location = New System.Drawing.Point(79, 58)
        Me.txt_cantidad.Name = "txt_cantidad"
        Me.txt_cantidad.Size = New System.Drawing.Size(46, 20)
        Me.txt_cantidad.TabIndex = 29
        '
        'txt_codigoProducto
        '
        Me.txt_codigoProducto.Location = New System.Drawing.Point(79, 13)
        Me.txt_codigoProducto.Name = "txt_codigoProducto"
        Me.txt_codigoProducto.Size = New System.Drawing.Size(46, 20)
        Me.txt_codigoProducto.TabIndex = 28
        '
        'cmd_salir
        '
        Me.cmd_salir.Image = Global.MenuProyecto.My.Resources.Resources.puerta
        Me.cmd_salir.Location = New System.Drawing.Point(512, 8)
        Me.cmd_salir.Name = "cmd_salir"
        Me.cmd_salir.Size = New System.Drawing.Size(45, 45)
        Me.cmd_salir.TabIndex = 32
        Me.ToolTip1.SetToolTip(Me.cmd_salir, "Salir")
        Me.cmd_salir.UseVisualStyleBackColor = True
        '
        'cmd_cancelar
        '
        Me.cmd_cancelar.Image = Global.MenuProyecto.My.Resources.Resources.splashcancelar
        Me.cmd_cancelar.Location = New System.Drawing.Point(112, 8)
        Me.cmd_cancelar.Name = "cmd_cancelar"
        Me.cmd_cancelar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_cancelar.TabIndex = 31
        Me.ToolTip1.SetToolTip(Me.cmd_cancelar, "Cancelar")
        Me.cmd_cancelar.UseVisualStyleBackColor = True
        '
        'cmd_guardar
        '
        Me.cmd_guardar.Image = Global.MenuProyecto.My.Resources.Resources.Grabar2
        Me.cmd_guardar.Location = New System.Drawing.Point(61, 8)
        Me.cmd_guardar.Name = "cmd_guardar"
        Me.cmd_guardar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_guardar.TabIndex = 30
        Me.ToolTip1.SetToolTip(Me.cmd_guardar, "Guardar")
        Me.cmd_guardar.UseVisualStyleBackColor = True
        '
        'cmd_nuevo
        '
        Me.cmd_nuevo.Image = Global.MenuProyecto.My.Resources.Resources.Nuevo2
        Me.cmd_nuevo.Location = New System.Drawing.Point(10, 8)
        Me.cmd_nuevo.Name = "cmd_nuevo"
        Me.cmd_nuevo.Size = New System.Drawing.Size(45, 45)
        Me.cmd_nuevo.TabIndex = 29
        Me.ToolTip1.SetToolTip(Me.cmd_nuevo, "Nuevo")
        Me.cmd_nuevo.UseVisualStyleBackColor = True
        '
        'Panel2
        '
        Me.Panel2.Controls.Add(Me.cmd_buscarFacturas)
        Me.Panel2.Controls.Add(Me.cmd_salir)
        Me.Panel2.Controls.Add(Me.cmd_cancelar)
        Me.Panel2.Controls.Add(Me.cmd_guardar)
        Me.Panel2.Controls.Add(Me.cmd_nuevo)
        Me.Panel2.Location = New System.Drawing.Point(19, 489)
        Me.Panel2.Name = "Panel2"
        Me.Panel2.Size = New System.Drawing.Size(560, 63)
        Me.Panel2.TabIndex = 33
        '
        'cmd_buscarFacturas
        '
        Me.cmd_buscarFacturas.Image = Global.MenuProyecto.My.Resources.Resources.BUSCAR01
        Me.cmd_buscarFacturas.Location = New System.Drawing.Point(163, 8)
        Me.cmd_buscarFacturas.Name = "cmd_buscarFacturas"
        Me.cmd_buscarFacturas.Size = New System.Drawing.Size(45, 45)
        Me.cmd_buscarFacturas.TabIndex = 33
        Me.ToolTip1.SetToolTip(Me.cmd_buscarFacturas, "Mostrar Facturas")
        Me.cmd_buscarFacturas.UseVisualStyleBackColor = True
        '
        'Label11
        '
        Me.Label11.AutoSize = True
        Me.Label11.Location = New System.Drawing.Point(410, 470)
        Me.Label11.Name = "Label11"
        Me.Label11.Size = New System.Drawing.Size(31, 13)
        Me.Label11.TabIndex = 35
        Me.Label11.Text = "Total"
        '
        'txt_apellido
        '
        Me.txt_apellido.Enabled = False
        Me.txt_apellido.Location = New System.Drawing.Point(283, 96)
        Me.txt_apellido.Name = "txt_apellido"
        Me.txt_apellido.Size = New System.Drawing.Size(94, 20)
        Me.txt_apellido.TabIndex = 36
        '
        'txt_nombre
        '
        Me.txt_nombre.Enabled = False
        Me.txt_nombre.Location = New System.Drawing.Point(439, 94)
        Me.txt_nombre.Name = "txt_nombre"
        Me.txt_nombre.Size = New System.Drawing.Size(121, 20)
        Me.txt_nombre.TabIndex = 37
        '
        'txt_totalfactura
        '
        Me.txt_totalfactura.Enabled = False
        Me.txt_totalfactura.Location = New System.Drawing.Point(456, 463)
        Me.txt_totalfactura.Name = "txt_totalfactura"
        Me.txt_totalfactura.Size = New System.Drawing.Size(121, 20)
        Me.txt_totalfactura.TabIndex = 38
        '
        'txt_fechaEmision
        '
        Me.txt_fechaEmision.Enabled = False
        Me.txt_fechaEmision.Location = New System.Drawing.Point(59, 25)
        Me.txt_fechaEmision.Mask = "00/00/0000"
        Me.txt_fechaEmision.Name = "txt_fechaEmision"
        Me.txt_fechaEmision.Size = New System.Drawing.Size(70, 20)
        Me.txt_fechaEmision.TabIndex = 39
        Me.txt_fechaEmision.ValidatingType = GetType(Date)
        '
        'Label12
        '
        Me.Label12.AutoSize = True
        Me.Label12.Location = New System.Drawing.Point(198, 129)
        Me.Label12.Name = "Label12"
        Me.Label12.Size = New System.Drawing.Size(79, 13)
        Me.Label12.TabIndex = 40
        Me.Label12.Text = "Medio de Pago"
        '
        'cmb_medio_pago
        '
        Me.cmb_medio_pago.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_medio_pago.FormattingEnabled = True
        Me.cmb_medio_pago.Location = New System.Drawing.Point(283, 129)
        Me.cmb_medio_pago.Name = "cmb_medio_pago"
        Me.cmb_medio_pago.Size = New System.Drawing.Size(121, 21)
        Me.cmb_medio_pago.TabIndex = 41
        '
        'frm_Factura
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(591, 561)
        Me.Controls.Add(Me.cmb_medio_pago)
        Me.Controls.Add(Me.Label12)
        Me.Controls.Add(Me.txt_fechaEmision)
        Me.Controls.Add(Me.txt_totalfactura)
        Me.Controls.Add(Me.txt_nombre)
        Me.Controls.Add(Me.txt_apellido)
        Me.Controls.Add(Me.Label11)
        Me.Controls.Add(Me.Panel2)
        Me.Controls.Add(Me.Panel1)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.cmd_buscarCliente)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.txt_nrodocumento)
        Me.Controls.Add(Me.cmb_tipodocumento)
        Me.Controls.Add(Me.GroupBox1)
        Me.Controls.Add(Me.Label1)
        Me.MaximizeBox = False
        Me.MaximumSize = New System.Drawing.Size(607, 600)
        Me.MinimumSize = New System.Drawing.Size(607, 600)
        Me.Name = "frm_Factura"
        Me.Text = "Factura"
        Me.GroupBox1.ResumeLayout(False)
        Me.GroupBox1.PerformLayout()
        Me.Panel1.ResumeLayout(False)
        Me.Panel1.PerformLayout()
        CType(Me.grid_detalle, System.ComponentModel.ISupportInitialize).EndInit()
        Me.Panel2.ResumeLayout(False)
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents opt_a As System.Windows.Forms.RadioButton
    Friend WithEvents opt_b As System.Windows.Forms.RadioButton
    Friend WithEvents opt_c As System.Windows.Forms.RadioButton
    Friend WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents cmb_tipodocumento As System.Windows.Forms.ComboBox
    Friend WithEvents txt_nrodocumento As System.Windows.Forms.TextBox
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents cmd_buscarCliente As System.Windows.Forms.Button
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents Label7 As System.Windows.Forms.Label
    Friend WithEvents cmb_buscarProducto As System.Windows.Forms.Button
    Friend WithEvents Label8 As System.Windows.Forms.Label
    Friend WithEvents Label9 As System.Windows.Forms.Label
    Friend WithEvents Label10 As System.Windows.Forms.Label
    Friend WithEvents cmd_agregar As System.Windows.Forms.Button
    Friend WithEvents Panel1 As System.Windows.Forms.Panel
    Friend WithEvents cmd_salir As System.Windows.Forms.Button
    Friend WithEvents cmd_cancelar As System.Windows.Forms.Button
    Friend WithEvents cmd_guardar As System.Windows.Forms.Button
    Friend WithEvents cmd_nuevo As System.Windows.Forms.Button
    Friend WithEvents Panel2 As System.Windows.Forms.Panel
    Friend WithEvents Label11 As System.Windows.Forms.Label
    Friend WithEvents txt_nombreProducto As System.Windows.Forms.TextBox
    Friend WithEvents txt_totalProducto As System.Windows.Forms.TextBox
    Friend WithEvents txt_precio As System.Windows.Forms.TextBox
    Friend WithEvents txt_cantidad As System.Windows.Forms.TextBox
    Friend WithEvents txt_codigoProducto As System.Windows.Forms.TextBox
    Friend WithEvents txt_apellido As System.Windows.Forms.TextBox
    Friend WithEvents txt_nombre As System.Windows.Forms.TextBox
    Friend WithEvents txt_totalfactura As System.Windows.Forms.TextBox
    Friend WithEvents txt_fechaEmision As System.Windows.Forms.MaskedTextBox
    Friend WithEvents grid_detalle As System.Windows.Forms.DataGridView
    Friend WithEvents Cantidad As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Id As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Articulo As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Precio As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Subtotal As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Label12 As System.Windows.Forms.Label
    Friend WithEvents cmb_medio_pago As System.Windows.Forms.ComboBox
    Friend WithEvents cmd_buscarFacturas As System.Windows.Forms.Button
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
    Friend WithEvents cmd_catalogo As System.Windows.Forms.Button
End Class
