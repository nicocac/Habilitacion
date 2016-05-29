<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frm_elcampito
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
        Me.cmd_salir = New System.Windows.Forms.Button()
        Me.MenuStrip1 = New System.Windows.Forms.MenuStrip()
        Me.CargarToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.item_cliente = New System.Windows.Forms.ToolStripMenuItem()
        Me.item_proovedor = New System.Windows.Forms.ToolStripMenuItem()
        Me.item_producto = New System.Windows.Forms.ToolStripMenuItem()
        Me.LocalidadToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.item_localidad = New System.Windows.Forms.ToolStripMenuItem()
        Me.item_barrio = New System.Windows.Forms.ToolStripMenuItem()
        Me.ReToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.item_venta = New System.Windows.Forms.ToolStripMenuItem()
        Me.item_compra = New System.Windows.Forms.ToolStripMenuItem()
        Me.EstadísticasToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.ListadosToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.EstadísticasToolStripMenuItem1 = New System.Windows.Forms.ToolStripMenuItem()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.MenuStrip1.SuspendLayout()
        Me.SuspendLayout()
        '
        'cmd_salir
        '
        Me.cmd_salir.Image = Global.MenuProyecto.My.Resources.Resources.puerta
        Me.cmd_salir.Location = New System.Drawing.Point(919, 484)
        Me.cmd_salir.Name = "cmd_salir"
        Me.cmd_salir.Size = New System.Drawing.Size(45, 45)
        Me.cmd_salir.TabIndex = 0
        Me.ToolTip1.SetToolTip(Me.cmd_salir, "Salir")
        Me.cmd_salir.UseVisualStyleBackColor = True
        '
        'MenuStrip1
        '
        Me.MenuStrip1.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.CargarToolStripMenuItem, Me.ReToolStripMenuItem, Me.EstadísticasToolStripMenuItem})
        Me.MenuStrip1.Location = New System.Drawing.Point(0, 0)
        Me.MenuStrip1.Name = "MenuStrip1"
        Me.MenuStrip1.Size = New System.Drawing.Size(976, 24)
        Me.MenuStrip1.TabIndex = 1
        Me.MenuStrip1.Text = "MenuStrip1"
        '
        'CargarToolStripMenuItem
        '
        Me.CargarToolStripMenuItem.DropDownItems.AddRange(New System.Windows.Forms.ToolStripItem() {Me.item_cliente, Me.item_proovedor, Me.item_producto, Me.LocalidadToolStripMenuItem})
        Me.CargarToolStripMenuItem.Name = "CargarToolStripMenuItem"
        Me.CargarToolStripMenuItem.Size = New System.Drawing.Size(54, 20)
        Me.CargarToolStripMenuItem.Text = "Cargar"
        '
        'item_cliente
        '
        Me.item_cliente.Name = "item_cliente"
        Me.item_cliente.Size = New System.Drawing.Size(128, 22)
        Me.item_cliente.Text = "Cliente"
        '
        'item_proovedor
        '
        Me.item_proovedor.Name = "item_proovedor"
        Me.item_proovedor.Size = New System.Drawing.Size(128, 22)
        Me.item_proovedor.Text = "Proveedor"
        '
        'item_producto
        '
        Me.item_producto.Name = "item_producto"
        Me.item_producto.Size = New System.Drawing.Size(128, 22)
        Me.item_producto.Text = "Producto"
        '
        'LocalidadToolStripMenuItem
        '
        Me.LocalidadToolStripMenuItem.DropDownItems.AddRange(New System.Windows.Forms.ToolStripItem() {Me.item_localidad, Me.item_barrio})
        Me.LocalidadToolStripMenuItem.Name = "LocalidadToolStripMenuItem"
        Me.LocalidadToolStripMenuItem.Size = New System.Drawing.Size(128, 22)
        Me.LocalidadToolStripMenuItem.Text = "Ubicación"
        '
        'item_localidad
        '
        Me.item_localidad.Name = "item_localidad"
        Me.item_localidad.Size = New System.Drawing.Size(125, 22)
        Me.item_localidad.Text = "Localidad"
        '
        'item_barrio
        '
        Me.item_barrio.Name = "item_barrio"
        Me.item_barrio.Size = New System.Drawing.Size(125, 22)
        Me.item_barrio.Text = "Barrio"
        '
        'ReToolStripMenuItem
        '
        Me.ReToolStripMenuItem.DropDownItems.AddRange(New System.Windows.Forms.ToolStripItem() {Me.item_venta, Me.item_compra})
        Me.ReToolStripMenuItem.Name = "ReToolStripMenuItem"
        Me.ReToolStripMenuItem.Size = New System.Drawing.Size(83, 20)
        Me.ReToolStripMenuItem.Text = "Transacción"
        '
        'item_venta
        '
        Me.item_venta.Name = "item_venta"
        Me.item_venta.Size = New System.Drawing.Size(193, 22)
        Me.item_venta.Text = "Venta"
        '
        'item_compra
        '
        Me.item_compra.Name = "item_compra"
        Me.item_compra.Size = New System.Drawing.Size(193, 22)
        Me.item_compra.Text = "Actualización de Stock"
        '
        'EstadísticasToolStripMenuItem
        '
        Me.EstadísticasToolStripMenuItem.DropDownItems.AddRange(New System.Windows.Forms.ToolStripItem() {Me.ListadosToolStripMenuItem, Me.EstadísticasToolStripMenuItem1})
        Me.EstadísticasToolStripMenuItem.Name = "EstadísticasToolStripMenuItem"
        Me.EstadísticasToolStripMenuItem.Size = New System.Drawing.Size(65, 20)
        Me.EstadísticasToolStripMenuItem.Text = "Reportes"
        '
        'ListadosToolStripMenuItem
        '
        Me.ListadosToolStripMenuItem.Name = "ListadosToolStripMenuItem"
        Me.ListadosToolStripMenuItem.Size = New System.Drawing.Size(134, 22)
        Me.ListadosToolStripMenuItem.Text = "Listados"
        '
        'EstadísticasToolStripMenuItem1
        '
        Me.EstadísticasToolStripMenuItem1.Name = "EstadísticasToolStripMenuItem1"
        Me.EstadísticasToolStripMenuItem1.Size = New System.Drawing.Size(134, 22)
        Me.EstadísticasToolStripMenuItem1.Text = "Estadísticas"
        '
        'frm_elcampito
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.BackgroundImage = Global.MenuProyecto.My.Resources.Resources.Fondo_menu
        Me.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch
        Me.ClientSize = New System.Drawing.Size(976, 541)
        Me.Controls.Add(Me.cmd_salir)
        Me.Controls.Add(Me.MenuStrip1)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D
        Me.MainMenuStrip = Me.MenuStrip1
        Me.MaximizeBox = False
        Me.Name = "frm_elcampito"
        Me.Text = "El Campito"
        Me.MenuStrip1.ResumeLayout(False)
        Me.MenuStrip1.PerformLayout()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents cmd_salir As System.Windows.Forms.Button
    Friend WithEvents MenuStrip1 As System.Windows.Forms.MenuStrip
    Friend WithEvents CargarToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents item_cliente As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents item_proovedor As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents item_producto As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents ReToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents LocalidadToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents item_localidad As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents item_barrio As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents EstadísticasToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents item_venta As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents item_compra As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
    Friend WithEvents ListadosToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents EstadísticasToolStripMenuItem1 As System.Windows.Forms.ToolStripMenuItem

End Class
