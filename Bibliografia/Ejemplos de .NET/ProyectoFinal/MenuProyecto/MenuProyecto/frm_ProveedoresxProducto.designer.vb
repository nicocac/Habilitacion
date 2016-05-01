<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frm_ProveedoresxProducto
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
        Me.cmb_proveedor = New System.Windows.Forms.ComboBox()
        Me.cmb_producto = New System.Windows.Forms.ComboBox()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.cmd_grabar = New System.Windows.Forms.Button()
        Me.cmd_salir = New System.Windows.Forms.Button()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.SuspendLayout()
        '
        'cmb_proveedor
        '
        Me.cmb_proveedor.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_proveedor.FormattingEnabled = True
        Me.cmb_proveedor.Location = New System.Drawing.Point(68, 47)
        Me.cmb_proveedor.Name = "cmb_proveedor"
        Me.cmb_proveedor.Size = New System.Drawing.Size(164, 21)
        Me.cmb_proveedor.TabIndex = 5
        '
        'cmb_producto
        '
        Me.cmb_producto.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_producto.FormattingEnabled = True
        Me.cmb_producto.Location = New System.Drawing.Point(68, 19)
        Me.cmb_producto.Name = "cmb_producto"
        Me.cmb_producto.Size = New System.Drawing.Size(164, 21)
        Me.cmb_producto.TabIndex = 4
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(12, 22)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(50, 13)
        Me.Label2.TabIndex = 2
        Me.Label2.Text = "Producto"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(6, 50)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(56, 13)
        Me.Label1.TabIndex = 3
        Me.Label1.Text = "Proveedor"
        '
        'cmd_grabar
        '
        Me.cmd_grabar.Image = Global.MenuProyecto.My.Resources.Resources.Grabar2
        Me.cmd_grabar.Location = New System.Drawing.Point(9, 107)
        Me.cmd_grabar.Name = "cmd_grabar"
        Me.cmd_grabar.Size = New System.Drawing.Size(40, 40)
        Me.cmd_grabar.TabIndex = 7
        Me.ToolTip1.SetToolTip(Me.cmd_grabar, "Guardar")
        Me.cmd_grabar.UseVisualStyleBackColor = True
        '
        'cmd_salir
        '
        Me.cmd_salir.Image = Global.MenuProyecto.My.Resources.Resources.puerta
        Me.cmd_salir.Location = New System.Drawing.Point(214, 107)
        Me.cmd_salir.Name = "cmd_salir"
        Me.cmd_salir.Size = New System.Drawing.Size(40, 40)
        Me.cmd_salir.TabIndex = 8
        Me.ToolTip1.SetToolTip(Me.cmd_salir, "Salir")
        Me.cmd_salir.UseVisualStyleBackColor = True
        '
        'frm_ProveedoresxProducto
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(266, 159)
        Me.Controls.Add(Me.cmd_grabar)
        Me.Controls.Add(Me.cmd_salir)
        Me.Controls.Add(Me.cmb_proveedor)
        Me.Controls.Add(Me.cmb_producto)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Name = "frm_ProveedoresxProducto"
        Me.Text = "Asignar proveedor"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents cmb_proveedor As System.Windows.Forms.ComboBox
    Friend WithEvents cmb_producto As System.Windows.Forms.ComboBox
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents cmd_grabar As System.Windows.Forms.Button
    Friend WithEvents cmd_salir As System.Windows.Forms.Button
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
End Class
