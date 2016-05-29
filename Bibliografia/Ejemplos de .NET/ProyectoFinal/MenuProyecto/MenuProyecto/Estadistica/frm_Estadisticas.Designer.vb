<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frm_Estadisticas
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
        Dim ReportDataSource1 As Microsoft.Reporting.WinForms.ReportDataSource = New Microsoft.Reporting.WinForms.ReportDataSource()
        Dim ReportDataSource2 As Microsoft.Reporting.WinForms.ReportDataSource = New Microsoft.Reporting.WinForms.ReportDataSource()
        Dim ReportDataSource3 As Microsoft.Reporting.WinForms.ReportDataSource = New Microsoft.Reporting.WinForms.ReportDataSource()
        Me.DS_G_Producto = New System.Windows.Forms.BindingSource(Me.components)
        Me.DS_G_Cliente = New System.Windows.Forms.BindingSource(Me.components)
        Me.DS_U_Semestre = New System.Windows.Forms.BindingSource(Me.components)
        Me.TabControl1 = New System.Windows.Forms.TabControl()
        Me.TabPage1 = New System.Windows.Forms.TabPage()
        Me.cmd_graficar_producto = New System.Windows.Forms.Button()
        Me.Reporte_G_Producto = New Microsoft.Reporting.WinForms.ReportViewer()
        Me.TabPage3 = New System.Windows.Forms.TabPage()
        Me.cmd_graficar_cliente = New System.Windows.Forms.Button()
        Me.Reporte_G_Cliente = New Microsoft.Reporting.WinForms.ReportViewer()
        Me.TabPage2 = New System.Windows.Forms.TabPage()
        Me.cmd_graficar_u_semestre = New System.Windows.Forms.Button()
        Me.Reporte_U_Semestre = New Microsoft.Reporting.WinForms.ReportViewer()
        CType(Me.DS_G_Producto, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.DS_G_Cliente, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.DS_U_Semestre, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.TabControl1.SuspendLayout()
        Me.TabPage1.SuspendLayout()
        Me.TabPage3.SuspendLayout()
        Me.TabPage2.SuspendLayout()
        Me.SuspendLayout()
        '
        'DS_G_Cliente
        '
        Me.DS_G_Cliente.DataMember = "Ganancias_X_Cliente"
        Me.DS_G_Cliente.DataSource = GetType(MenuProyecto.DataSet2)
        '
        'TabControl1
        '
        Me.TabControl1.Anchor = CType((((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Bottom) _
                    Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.TabControl1.Controls.Add(Me.TabPage1)
        Me.TabControl1.Controls.Add(Me.TabPage3)
        Me.TabControl1.Controls.Add(Me.TabPage2)
        Me.TabControl1.Location = New System.Drawing.Point(1, 1)
        Me.TabControl1.Name = "TabControl1"
        Me.TabControl1.SelectedIndex = 0
        Me.TabControl1.Size = New System.Drawing.Size(852, 528)
        Me.TabControl1.TabIndex = 0
        '
        'TabPage1
        '
        Me.TabPage1.Controls.Add(Me.cmd_graficar_producto)
        Me.TabPage1.Controls.Add(Me.Reporte_G_Producto)
        Me.TabPage1.Location = New System.Drawing.Point(4, 22)
        Me.TabPage1.Name = "TabPage1"
        Me.TabPage1.Padding = New System.Windows.Forms.Padding(3)
        Me.TabPage1.Size = New System.Drawing.Size(844, 502)
        Me.TabPage1.TabIndex = 0
        Me.TabPage1.Text = "Ganancias Por Producto"
        Me.TabPage1.UseVisualStyleBackColor = True
        '
        'cmd_graficar_producto
        '
        Me.cmd_graficar_producto.Location = New System.Drawing.Point(0, 476)
        Me.cmd_graficar_producto.Name = "cmd_graficar_producto"
        Me.cmd_graficar_producto.Size = New System.Drawing.Size(105, 23)
        Me.cmd_graficar_producto.TabIndex = 1
        Me.cmd_graficar_producto.Text = "Actualizar"
        Me.cmd_graficar_producto.UseVisualStyleBackColor = True
        '
        'Reporte_G_Producto
        '
        Me.Reporte_G_Producto.Anchor = CType((((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Bottom) _
                    Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        ReportDataSource1.Name = "DataSet1"
        ReportDataSource1.Value = Me.DS_G_Producto
        Me.Reporte_G_Producto.LocalReport.DataSources.Add(ReportDataSource1)
        Me.Reporte_G_Producto.LocalReport.ReportEmbeddedResource = "MenuProyecto.Grafico_Producto.rdlc"
        Me.Reporte_G_Producto.Location = New System.Drawing.Point(-4, 0)
        Me.Reporte_G_Producto.Name = "Reporte_G_Producto"
        Me.Reporte_G_Producto.Size = New System.Drawing.Size(852, 470)
        Me.Reporte_G_Producto.TabIndex = 0
        '
        'TabPage3
        '
        Me.TabPage3.Controls.Add(Me.cmd_graficar_cliente)
        Me.TabPage3.Controls.Add(Me.Reporte_G_Cliente)
        Me.TabPage3.Location = New System.Drawing.Point(4, 22)
        Me.TabPage3.Name = "TabPage3"
        Me.TabPage3.Size = New System.Drawing.Size(844, 502)
        Me.TabPage3.TabIndex = 2
        Me.TabPage3.Text = "Ganancias Por Cliente"
        Me.TabPage3.UseVisualStyleBackColor = True
        '
        'cmd_graficar_cliente
        '
        Me.cmd_graficar_cliente.Location = New System.Drawing.Point(0, 476)
        Me.cmd_graficar_cliente.Name = "cmd_graficar_cliente"
        Me.cmd_graficar_cliente.Size = New System.Drawing.Size(105, 23)
        Me.cmd_graficar_cliente.TabIndex = 2
        Me.cmd_graficar_cliente.Text = "Actualizar"
        Me.cmd_graficar_cliente.UseVisualStyleBackColor = True
        '
        'Reporte_G_Cliente
        '
        Me.Reporte_G_Cliente.Anchor = CType((((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Bottom) _
                    Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        ReportDataSource2.Name = "DataSet2"
        ReportDataSource2.Value = Me.DS_G_Cliente
        Me.Reporte_G_Cliente.LocalReport.DataSources.Add(ReportDataSource2)
        Me.Reporte_G_Cliente.LocalReport.ReportEmbeddedResource = "MenuProyecto.Grafico_Cliente.rdlc"
        Me.Reporte_G_Cliente.Location = New System.Drawing.Point(-4, 0)
        Me.Reporte_G_Cliente.Name = "Reporte_G_Cliente"
        Me.Reporte_G_Cliente.Size = New System.Drawing.Size(848, 470)
        Me.Reporte_G_Cliente.TabIndex = 0
        '
        'TabPage2
        '
        Me.TabPage2.Controls.Add(Me.cmd_graficar_u_semestre)
        Me.TabPage2.Controls.Add(Me.Reporte_U_Semestre)
        Me.TabPage2.Location = New System.Drawing.Point(4, 22)
        Me.TabPage2.Name = "TabPage2"
        Me.TabPage2.Padding = New System.Windows.Forms.Padding(3)
        Me.TabPage2.Size = New System.Drawing.Size(844, 502)
        Me.TabPage2.TabIndex = 1
        Me.TabPage2.Text = "Ganancias Ultimo Semestre"
        Me.TabPage2.UseVisualStyleBackColor = True
        '
        'cmd_graficar_u_semestre
        '
        Me.cmd_graficar_u_semestre.Location = New System.Drawing.Point(0, 476)
        Me.cmd_graficar_u_semestre.Name = "cmd_graficar_u_semestre"
        Me.cmd_graficar_u_semestre.Size = New System.Drawing.Size(105, 23)
        Me.cmd_graficar_u_semestre.TabIndex = 2
        Me.cmd_graficar_u_semestre.Text = "Actualizar"
        Me.cmd_graficar_u_semestre.UseVisualStyleBackColor = True
        '
        'Reporte_U_Semestre
        '
        Me.Reporte_U_Semestre.Anchor = CType((((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Bottom) _
                    Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        ReportDataSource3.Name = "DataSet1"
        ReportDataSource3.Value = Me.DS_U_Semestre
        Me.Reporte_U_Semestre.LocalReport.DataSources.Add(ReportDataSource3)
        Me.Reporte_U_Semestre.LocalReport.ReportEmbeddedResource = "MenuProyecto.Grafico_Semestre.rdlc"
        Me.Reporte_U_Semestre.Location = New System.Drawing.Point(-3, -1)
        Me.Reporte_U_Semestre.Name = "Reporte_U_Semestre"
        Me.Reporte_U_Semestre.Size = New System.Drawing.Size(847, 471)
        Me.Reporte_U_Semestre.TabIndex = 0
        '
        'frm_Estadisticas
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(852, 528)
        Me.Controls.Add(Me.TabControl1)
        Me.MaximizeBox = False
        Me.MaximumSize = New System.Drawing.Size(868, 567)
        Me.MinimumSize = New System.Drawing.Size(868, 567)
        Me.Name = "frm_Estadisticas"
        Me.Text = "Estadisticas"
        CType(Me.DS_G_Producto, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.DS_G_Cliente, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.DS_U_Semestre, System.ComponentModel.ISupportInitialize).EndInit()
        Me.TabControl1.ResumeLayout(False)
        Me.TabPage1.ResumeLayout(False)
        Me.TabPage3.ResumeLayout(False)
        Me.TabPage2.ResumeLayout(False)
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents TabControl1 As System.Windows.Forms.TabControl
    Friend WithEvents TabPage1 As System.Windows.Forms.TabPage
    Friend WithEvents Reporte_G_Producto As Microsoft.Reporting.WinForms.ReportViewer
    Friend WithEvents TabPage3 As System.Windows.Forms.TabPage
    Friend WithEvents TabPage2 As System.Windows.Forms.TabPage
    Friend WithEvents Reporte_G_Cliente As Microsoft.Reporting.WinForms.ReportViewer
    Friend WithEvents Reporte_U_Semestre As Microsoft.Reporting.WinForms.ReportViewer
    Friend WithEvents DS_G_Producto As System.Windows.Forms.BindingSource
    Friend WithEvents DS_U_Semestre As System.Windows.Forms.BindingSource
    Friend WithEvents cmd_graficar_producto As System.Windows.Forms.Button
    Friend WithEvents cmd_graficar_cliente As System.Windows.Forms.Button
    Friend WithEvents cmd_graficar_u_semestre As System.Windows.Forms.Button
    Friend WithEvents DS_G_Cliente As System.Windows.Forms.BindingSource
End Class
