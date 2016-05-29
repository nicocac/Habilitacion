<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frm_Listados
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
        Dim ReportDataSource1 As Microsoft.Reporting.WinForms.ReportDataSource = New Microsoft.Reporting.WinForms.ReportDataSource()
        Dim ReportDataSource2 As Microsoft.Reporting.WinForms.ReportDataSource = New Microsoft.Reporting.WinForms.ReportDataSource()
        Dim ReportDataSource3 As Microsoft.Reporting.WinForms.ReportDataSource = New Microsoft.Reporting.WinForms.ReportDataSource()
        Dim ReportDataSource4 As Microsoft.Reporting.WinForms.ReportDataSource = New Microsoft.Reporting.WinForms.ReportDataSource()
        Dim ReportDataSource5 As Microsoft.Reporting.WinForms.ReportDataSource = New Microsoft.Reporting.WinForms.ReportDataSource()
        Me.DS_LP = New System.Windows.Forms.BindingSource(Me.components)
        Me.DS_LC = New System.Windows.Forms.BindingSource(Me.components)
        Me.DS_LF = New System.Windows.Forms.BindingSource(Me.components)
        Me.DS_LPRO = New System.Windows.Forms.BindingSource(Me.components)
        Me.TabControl1 = New System.Windows.Forms.TabControl()
        Me.TabPage1 = New System.Windows.Forms.TabPage()
        Me.cmd_grafico = New System.Windows.Forms.Button()
        Me.ReportProductos1 = New Microsoft.Reporting.WinForms.ReportViewer()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.cmd_buscar_producto = New System.Windows.Forms.Button()
        Me.txt_buscar_producto = New System.Windows.Forms.TextBox()
        Me.Clientes = New System.Windows.Forms.TabPage()
        Me.ReportClientes = New Microsoft.Reporting.WinForms.ReportViewer()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.cmd_buscar_cliente = New System.Windows.Forms.Button()
        Me.txt_buscar_cliente = New System.Windows.Forms.TextBox()
        Me.TabPage2 = New System.Windows.Forms.TabPage()
        Me.cmd_buscar_factura = New System.Windows.Forms.Button()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.cmb_meses = New System.Windows.Forms.ComboBox()
        Me.ReportFacturas = New Microsoft.Reporting.WinForms.ReportViewer()
        Me.TabPage3 = New System.Windows.Forms.TabPage()
        Me.ReportProveedores = New Microsoft.Reporting.WinForms.ReportViewer()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.cmd_buscar = New System.Windows.Forms.Button()
        Me.txt_buscar_proveedor = New System.Windows.Forms.TextBox()
        Me.ReportProductos = New Microsoft.Reporting.WinForms.ReportViewer()
        CType(Me.DS_LP, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.DS_LC, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.DS_LF, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.DS_LPRO, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.TabControl1.SuspendLayout()
        Me.TabPage1.SuspendLayout()
        Me.Clientes.SuspendLayout()
        Me.TabPage2.SuspendLayout()
        Me.TabPage3.SuspendLayout()
        Me.SuspendLayout()
        '
        'DS_LP
        '
        Me.DS_LP.DataMember = "Productos"
        Me.DS_LP.DataSource = GetType(MenuProyecto.DataSet1)
        '
        'DS_LC
        '
        Me.DS_LC.DataMember = "Cliente"
        Me.DS_LC.DataSource = GetType(MenuProyecto.DataSet1)
        '
        'DS_LF
        '
        Me.DS_LF.DataMember = "Factura"
        Me.DS_LF.DataSource = GetType(MenuProyecto.DataSet1)
        '
        'DS_LPRO
        '
        Me.DS_LPRO.DataMember = "Proveedores"
        Me.DS_LPRO.DataSource = GetType(MenuProyecto.DataSet1)
        '
        'TabControl1
        '
        Me.TabControl1.Controls.Add(Me.TabPage1)
        Me.TabControl1.Controls.Add(Me.Clientes)
        Me.TabControl1.Controls.Add(Me.TabPage2)
        Me.TabControl1.Controls.Add(Me.TabPage3)
        Me.TabControl1.Location = New System.Drawing.Point(4, 2)
        Me.TabControl1.Name = "TabControl1"
        Me.TabControl1.SelectedIndex = 0
        Me.TabControl1.Size = New System.Drawing.Size(933, 658)
        Me.TabControl1.TabIndex = 0
        '
        'TabPage1
        '
        Me.TabPage1.Controls.Add(Me.cmd_grafico)
        Me.TabPage1.Controls.Add(Me.ReportProductos1)
        Me.TabPage1.Controls.Add(Me.Label2)
        Me.TabPage1.Controls.Add(Me.cmd_buscar_producto)
        Me.TabPage1.Controls.Add(Me.txt_buscar_producto)
        Me.TabPage1.Location = New System.Drawing.Point(4, 22)
        Me.TabPage1.Name = "TabPage1"
        Me.TabPage1.Padding = New System.Windows.Forms.Padding(3)
        Me.TabPage1.Size = New System.Drawing.Size(925, 632)
        Me.TabPage1.TabIndex = 0
        Me.TabPage1.Text = "Productos en Stock"
        Me.TabPage1.UseVisualStyleBackColor = True
        '
        'cmd_grafico
        '
        Me.cmd_grafico.Location = New System.Drawing.Point(811, 599)
        Me.cmd_grafico.Name = "cmd_grafico"
        Me.cmd_grafico.Size = New System.Drawing.Size(108, 23)
        Me.cmd_grafico.TabIndex = 7
        Me.cmd_grafico.Text = "Catalogo Grafico"
        Me.cmd_grafico.UseVisualStyleBackColor = True
        '
        'ReportProductos1
        '
        ReportDataSource1.Name = "DataSet1"
        ReportDataSource1.Value = Me.DS_LP
        Me.ReportProductos1.LocalReport.DataSources.Add(ReportDataSource1)
        Me.ReportProductos1.LocalReport.ReportEmbeddedResource = "MenuProyecto.Listado_Productos.rdlc"
        Me.ReportProductos1.Location = New System.Drawing.Point(-3, 0)
        Me.ReportProductos1.Name = "ReportProductos1"
        Me.ReportProductos1.Size = New System.Drawing.Size(922, 593)
        Me.ReportProductos1.TabIndex = 6
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(2, 604)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(105, 13)
        Me.Label2.TabIndex = 5
        Me.Label2.Text = "Nombre de Producto"
        '
        'cmd_buscar_producto
        '
        Me.cmd_buscar_producto.Location = New System.Drawing.Point(219, 599)
        Me.cmd_buscar_producto.Name = "cmd_buscar_producto"
        Me.cmd_buscar_producto.Size = New System.Drawing.Size(75, 23)
        Me.cmd_buscar_producto.TabIndex = 4
        Me.cmd_buscar_producto.Text = "Buscar"
        Me.cmd_buscar_producto.UseVisualStyleBackColor = True
        '
        'txt_buscar_producto
        '
        Me.txt_buscar_producto.Location = New System.Drawing.Point(113, 601)
        Me.txt_buscar_producto.Name = "txt_buscar_producto"
        Me.txt_buscar_producto.Size = New System.Drawing.Size(100, 20)
        Me.txt_buscar_producto.TabIndex = 3
        '
        'Clientes
        '
        Me.Clientes.Controls.Add(Me.ReportClientes)
        Me.Clientes.Controls.Add(Me.Label1)
        Me.Clientes.Controls.Add(Me.cmd_buscar_cliente)
        Me.Clientes.Controls.Add(Me.txt_buscar_cliente)
        Me.Clientes.Location = New System.Drawing.Point(4, 22)
        Me.Clientes.Name = "Clientes"
        Me.Clientes.Padding = New System.Windows.Forms.Padding(3)
        Me.Clientes.Size = New System.Drawing.Size(925, 632)
        Me.Clientes.TabIndex = 1
        Me.Clientes.Text = "Clientes"
        Me.Clientes.UseVisualStyleBackColor = True
        '
        'ReportClientes
        '
        ReportDataSource2.Name = "DataSet1"
        ReportDataSource2.Value = Me.DS_LC
        Me.ReportClientes.LocalReport.DataSources.Add(ReportDataSource2)
        Me.ReportClientes.LocalReport.ReportEmbeddedResource = "MenuProyecto.Listado_Clientes.rdlc"
        Me.ReportClientes.Location = New System.Drawing.Point(-4, 0)
        Me.ReportClientes.Name = "ReportClientes"
        Me.ReportClientes.Size = New System.Drawing.Size(929, 601)
        Me.ReportClientes.TabIndex = 3
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(3, 612)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(97, 13)
        Me.Label1.TabIndex = 2
        Me.Label1.Text = "Nro de Documento"
        '
        'cmd_buscar_cliente
        '
        Me.cmd_buscar_cliente.Location = New System.Drawing.Point(212, 607)
        Me.cmd_buscar_cliente.Name = "cmd_buscar_cliente"
        Me.cmd_buscar_cliente.Size = New System.Drawing.Size(75, 23)
        Me.cmd_buscar_cliente.TabIndex = 1
        Me.cmd_buscar_cliente.Text = "Buscar"
        Me.cmd_buscar_cliente.UseVisualStyleBackColor = True
        '
        'txt_buscar_cliente
        '
        Me.txt_buscar_cliente.Location = New System.Drawing.Point(106, 609)
        Me.txt_buscar_cliente.Name = "txt_buscar_cliente"
        Me.txt_buscar_cliente.Size = New System.Drawing.Size(100, 20)
        Me.txt_buscar_cliente.TabIndex = 0
        '
        'TabPage2
        '
        Me.TabPage2.Controls.Add(Me.cmd_buscar_factura)
        Me.TabPage2.Controls.Add(Me.Label3)
        Me.TabPage2.Controls.Add(Me.cmb_meses)
        Me.TabPage2.Controls.Add(Me.ReportFacturas)
        Me.TabPage2.Location = New System.Drawing.Point(4, 22)
        Me.TabPage2.Name = "TabPage2"
        Me.TabPage2.Padding = New System.Windows.Forms.Padding(3)
        Me.TabPage2.Size = New System.Drawing.Size(925, 632)
        Me.TabPage2.TabIndex = 2
        Me.TabPage2.Text = "Facturas por mes"
        Me.TabPage2.UseVisualStyleBackColor = True
        '
        'cmd_buscar_factura
        '
        Me.cmd_buscar_factura.Location = New System.Drawing.Point(213, 599)
        Me.cmd_buscar_factura.Name = "cmd_buscar_factura"
        Me.cmd_buscar_factura.Size = New System.Drawing.Size(75, 23)
        Me.cmd_buscar_factura.TabIndex = 3
        Me.cmd_buscar_factura.Text = "Buscar"
        Me.cmd_buscar_factura.UseVisualStyleBackColor = True
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(4, 604)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(82, 13)
        Me.Label3.TabIndex = 2
        Me.Label3.Text = "Seleccione mes"
        '
        'cmb_meses
        '
        Me.cmb_meses.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_meses.FormattingEnabled = True
        Me.cmb_meses.Items.AddRange(New Object() {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"})
        Me.cmb_meses.Location = New System.Drawing.Point(92, 601)
        Me.cmb_meses.Name = "cmb_meses"
        Me.cmb_meses.Size = New System.Drawing.Size(115, 21)
        Me.cmb_meses.TabIndex = 1
        '
        'ReportFacturas
        '
        ReportDataSource3.Name = "DataSet1"
        ReportDataSource3.Value = Me.DS_LF
        Me.ReportFacturas.LocalReport.DataSources.Add(ReportDataSource3)
        Me.ReportFacturas.LocalReport.ReportEmbeddedResource = "MenuProyecto.Listado_Facturas.rdlc"
        Me.ReportFacturas.Location = New System.Drawing.Point(-4, 0)
        Me.ReportFacturas.Name = "ReportFacturas"
        Me.ReportFacturas.Size = New System.Drawing.Size(926, 593)
        Me.ReportFacturas.TabIndex = 0
        '
        'TabPage3
        '
        Me.TabPage3.Controls.Add(Me.ReportProveedores)
        Me.TabPage3.Controls.Add(Me.Label4)
        Me.TabPage3.Controls.Add(Me.cmd_buscar)
        Me.TabPage3.Controls.Add(Me.txt_buscar_proveedor)
        Me.TabPage3.Location = New System.Drawing.Point(4, 22)
        Me.TabPage3.Name = "TabPage3"
        Me.TabPage3.Padding = New System.Windows.Forms.Padding(3)
        Me.TabPage3.Size = New System.Drawing.Size(925, 632)
        Me.TabPage3.TabIndex = 3
        Me.TabPage3.Text = "Proveedores"
        Me.TabPage3.UseVisualStyleBackColor = True
        '
        'ReportProveedores
        '
        ReportDataSource4.Name = "DataSet1"
        ReportDataSource4.Value = Me.DS_LPRO
        Me.ReportProveedores.LocalReport.DataSources.Add(ReportDataSource4)
        Me.ReportProveedores.LocalReport.ReportEmbeddedResource = "MenuProyecto.Listado_Proveedores.rdlc"
        Me.ReportProveedores.Location = New System.Drawing.Point(-4, 0)
        Me.ReportProveedores.Name = "ReportProveedores"
        Me.ReportProveedores.Size = New System.Drawing.Size(923, 603)
        Me.ReportProveedores.TabIndex = 6
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(6, 612)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(70, 13)
        Me.Label4.TabIndex = 5
        Me.Label4.Text = "Razón Social"
        '
        'cmd_buscar
        '
        Me.cmd_buscar.Location = New System.Drawing.Point(188, 607)
        Me.cmd_buscar.Name = "cmd_buscar"
        Me.cmd_buscar.Size = New System.Drawing.Size(75, 23)
        Me.cmd_buscar.TabIndex = 4
        Me.cmd_buscar.Text = "Buscar"
        Me.cmd_buscar.UseVisualStyleBackColor = True
        '
        'txt_buscar_proveedor
        '
        Me.txt_buscar_proveedor.Location = New System.Drawing.Point(82, 609)
        Me.txt_buscar_proveedor.Name = "txt_buscar_proveedor"
        Me.txt_buscar_proveedor.Size = New System.Drawing.Size(100, 20)
        Me.txt_buscar_proveedor.TabIndex = 3
        '
        'ReportProductos
        '
        ReportDataSource5.Name = "DataSet1"
        ReportDataSource5.Value = Nothing
        Me.ReportProductos.LocalReport.DataSources.Add(ReportDataSource5)
        Me.ReportProductos.LocalReport.ReportEmbeddedResource = "MenuProyecto.Listado_Productos.rdlc"
        Me.ReportProductos.Location = New System.Drawing.Point(-4, 0)
        Me.ReportProductos.Name = "ReportProductos"
        Me.ReportProductos.Size = New System.Drawing.Size(698, 344)
        Me.ReportProductos.TabIndex = 0
        '
        'frm_Listados
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(940, 658)
        Me.Controls.Add(Me.TabControl1)
        Me.MaximizeBox = False
        Me.MaximumSize = New System.Drawing.Size(956, 697)
        Me.MinimumSize = New System.Drawing.Size(956, 697)
        Me.Name = "frm_Listados"
        Me.Text = "Listados"
        CType(Me.DS_LP, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.DS_LC, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.DS_LF, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.DS_LPRO, System.ComponentModel.ISupportInitialize).EndInit()
        Me.TabControl1.ResumeLayout(False)
        Me.TabPage1.ResumeLayout(False)
        Me.TabPage1.PerformLayout()
        Me.Clientes.ResumeLayout(False)
        Me.Clientes.PerformLayout()
        Me.TabPage2.ResumeLayout(False)
        Me.TabPage2.PerformLayout()
        Me.TabPage3.ResumeLayout(False)
        Me.TabPage3.PerformLayout()
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents TabControl1 As System.Windows.Forms.TabControl
    Friend WithEvents Clientes As System.Windows.Forms.TabPage
    Friend WithEvents ReportProductos As Microsoft.Reporting.WinForms.ReportViewer
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents cmd_buscar_cliente As System.Windows.Forms.Button
    Friend WithEvents txt_buscar_cliente As System.Windows.Forms.TextBox
    Friend WithEvents ReportClientes As Microsoft.Reporting.WinForms.ReportViewer
    Friend WithEvents DS_LC As System.Windows.Forms.BindingSource
    Friend WithEvents TabPage2 As System.Windows.Forms.TabPage
    Friend WithEvents ReportFacturas As Microsoft.Reporting.WinForms.ReportViewer
    Friend WithEvents cmb_meses As System.Windows.Forms.ComboBox
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents cmd_buscar_factura As System.Windows.Forms.Button
    Friend WithEvents DS_LF As System.Windows.Forms.BindingSource
    Friend WithEvents TabPage3 As System.Windows.Forms.TabPage
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents cmd_buscar As System.Windows.Forms.Button
    Friend WithEvents txt_buscar_proveedor As System.Windows.Forms.TextBox
    Friend WithEvents TabPage1 As System.Windows.Forms.TabPage
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents cmd_buscar_producto As System.Windows.Forms.Button
    Friend WithEvents txt_buscar_producto As System.Windows.Forms.TextBox
    Friend WithEvents ReportProductos1 As Microsoft.Reporting.WinForms.ReportViewer
    Friend WithEvents DS_LP As System.Windows.Forms.BindingSource
    Friend WithEvents ReportProveedores As Microsoft.Reporting.WinForms.ReportViewer
    Friend WithEvents DS_LPRO As System.Windows.Forms.BindingSource
    Friend WithEvents cmd_grafico As System.Windows.Forms.Button
End Class
