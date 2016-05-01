<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frm_Localidad
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(frm_Localidad))
        Me.grid_localidad = New System.Windows.Forms.DataGridView()
        Me.id_localidad = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.n_localidad = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.n_provincia = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.cmb_provincia = New System.Windows.Forms.ComboBox()
        Me.txt_nombre = New System.Windows.Forms.MaskedTextBox()
        Me.lbl_provincia = New System.Windows.Forms.Label()
        Me.lbl_nombre = New System.Windows.Forms.Label()
        Me.cmd_cancelar = New System.Windows.Forms.Button()
        Me.cmd_grabar = New System.Windows.Forms.Button()
        Me.cmd_nuevo = New System.Windows.Forms.Button()
        Me.cmd_salir = New System.Windows.Forms.Button()
        Me.cmd_eliminar = New System.Windows.Forms.Button()
        Me.gbox_buscador = New System.Windows.Forms.GroupBox()
        Me.cmd_limpiar = New System.Windows.Forms.Button()
        Me.txt_descripcion = New System.Windows.Forms.MaskedTextBox()
        Me.lbl_buscador = New System.Windows.Forms.Label()
        Me.cmd_modificar = New System.Windows.Forms.Button()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        CType(Me.grid_localidad, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.gbox_buscador.SuspendLayout()
        Me.SuspendLayout()
        '
        'grid_localidad
        '
        Me.grid_localidad.AllowUserToAddRows = False
        Me.grid_localidad.AllowUserToDeleteRows = False
        Me.grid_localidad.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.grid_localidad.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.id_localidad, Me.n_localidad, Me.n_provincia})
        Me.grid_localidad.Location = New System.Drawing.Point(214, 12)
        Me.grid_localidad.Name = "grid_localidad"
        Me.grid_localidad.ReadOnly = True
        Me.grid_localidad.Size = New System.Drawing.Size(298, 178)
        Me.grid_localidad.TabIndex = 34
        '
        'id_localidad
        '
        Me.id_localidad.HeaderText = "Id "
        Me.id_localidad.Name = "id_localidad"
        Me.id_localidad.ReadOnly = True
        Me.id_localidad.Width = 40
        '
        'n_localidad
        '
        Me.n_localidad.HeaderText = "Localidad"
        Me.n_localidad.Name = "n_localidad"
        Me.n_localidad.ReadOnly = True
        '
        'n_provincia
        '
        Me.n_provincia.HeaderText = "Provincia"
        Me.n_provincia.Name = "n_provincia"
        Me.n_provincia.ReadOnly = True
        '
        'cmb_provincia
        '
        Me.cmb_provincia.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_provincia.FormattingEnabled = True
        Me.cmb_provincia.Location = New System.Drawing.Point(84, 55)
        Me.cmb_provincia.Name = "cmb_provincia"
        Me.cmb_provincia.Size = New System.Drawing.Size(121, 21)
        Me.cmb_provincia.TabIndex = 2
        '
        'txt_nombre
        '
        Me.txt_nombre.Location = New System.Drawing.Point(84, 22)
        Me.txt_nombre.Name = "txt_nombre"
        Me.txt_nombre.Size = New System.Drawing.Size(100, 20)
        Me.txt_nombre.TabIndex = 1
        '
        'lbl_provincia
        '
        Me.lbl_provincia.AutoSize = True
        Me.lbl_provincia.Location = New System.Drawing.Point(24, 58)
        Me.lbl_provincia.Name = "lbl_provincia"
        Me.lbl_provincia.Size = New System.Drawing.Size(51, 13)
        Me.lbl_provincia.TabIndex = 30
        Me.lbl_provincia.Text = "Provincia"
        '
        'lbl_nombre
        '
        Me.lbl_nombre.AutoSize = True
        Me.lbl_nombre.Location = New System.Drawing.Point(31, 25)
        Me.lbl_nombre.Name = "lbl_nombre"
        Me.lbl_nombre.Size = New System.Drawing.Size(44, 13)
        Me.lbl_nombre.TabIndex = 31
        Me.lbl_nombre.Text = "Nombre"
        '
        'cmd_cancelar
        '
        Me.cmd_cancelar.Image = CType(resources.GetObject("cmd_cancelar.Image"), System.Drawing.Image)
        Me.cmd_cancelar.Location = New System.Drawing.Point(231, 267)
        Me.cmd_cancelar.Name = "cmd_cancelar"
        Me.cmd_cancelar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_cancelar.TabIndex = 6
        Me.ToolTip1.SetToolTip(Me.cmd_cancelar, "Cancelar")
        Me.cmd_cancelar.UseVisualStyleBackColor = True
        '
        'cmd_grabar
        '
        Me.cmd_grabar.Enabled = False
        Me.cmd_grabar.Image = CType(resources.GetObject("cmd_grabar.Image"), System.Drawing.Image)
        Me.cmd_grabar.Location = New System.Drawing.Point(78, 267)
        Me.cmd_grabar.Name = "cmd_grabar"
        Me.cmd_grabar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_grabar.TabIndex = 4
        Me.ToolTip1.SetToolTip(Me.cmd_grabar, "Guardar")
        Me.cmd_grabar.UseVisualStyleBackColor = True
        '
        'cmd_nuevo
        '
        Me.cmd_nuevo.Image = CType(resources.GetObject("cmd_nuevo.Image"), System.Drawing.Image)
        Me.cmd_nuevo.Location = New System.Drawing.Point(27, 267)
        Me.cmd_nuevo.Name = "cmd_nuevo"
        Me.cmd_nuevo.Size = New System.Drawing.Size(45, 45)
        Me.cmd_nuevo.TabIndex = 3
        Me.ToolTip1.SetToolTip(Me.cmd_nuevo, "Nuevo")
        Me.cmd_nuevo.UseVisualStyleBackColor = True
        '
        'cmd_salir
        '
        Me.cmd_salir.Image = Global.MenuProyecto.My.Resources.Resources.puerta
        Me.cmd_salir.Location = New System.Drawing.Point(467, 267)
        Me.cmd_salir.Name = "cmd_salir"
        Me.cmd_salir.Size = New System.Drawing.Size(45, 45)
        Me.cmd_salir.TabIndex = 8
        Me.ToolTip1.SetToolTip(Me.cmd_salir, "Salir")
        Me.cmd_salir.UseVisualStyleBackColor = True
        '
        'cmd_eliminar
        '
        Me.cmd_eliminar.Image = Global.MenuProyecto.My.Resources.Resources.Icono_eliminar2
        Me.cmd_eliminar.Location = New System.Drawing.Point(180, 267)
        Me.cmd_eliminar.Name = "cmd_eliminar"
        Me.cmd_eliminar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_eliminar.TabIndex = 5
        Me.ToolTip1.SetToolTip(Me.cmd_eliminar, "Eliminar")
        Me.cmd_eliminar.UseVisualStyleBackColor = True
        '
        'gbox_buscador
        '
        Me.gbox_buscador.Controls.Add(Me.cmd_limpiar)
        Me.gbox_buscador.Controls.Add(Me.txt_descripcion)
        Me.gbox_buscador.Controls.Add(Me.lbl_buscador)
        Me.gbox_buscador.Location = New System.Drawing.Point(214, 196)
        Me.gbox_buscador.Name = "gbox_buscador"
        Me.gbox_buscador.Size = New System.Drawing.Size(298, 64)
        Me.gbox_buscador.TabIndex = 0
        Me.gbox_buscador.TabStop = False
        Me.gbox_buscador.Text = "Buscador"
        '
        'cmd_limpiar
        '
        Me.cmd_limpiar.Image = Global.MenuProyecto.My.Resources.Resources.icono_x
        Me.cmd_limpiar.Location = New System.Drawing.Point(243, 22)
        Me.cmd_limpiar.Name = "cmd_limpiar"
        Me.cmd_limpiar.Size = New System.Drawing.Size(20, 20)
        Me.cmd_limpiar.TabIndex = 6
        Me.cmd_limpiar.UseVisualStyleBackColor = True
        '
        'txt_descripcion
        '
        Me.txt_descripcion.Location = New System.Drawing.Point(74, 23)
        Me.txt_descripcion.Name = "txt_descripcion"
        Me.txt_descripcion.Size = New System.Drawing.Size(163, 20)
        Me.txt_descripcion.TabIndex = 1
        '
        'lbl_buscador
        '
        Me.lbl_buscador.AutoSize = True
        Me.lbl_buscador.Location = New System.Drawing.Point(12, 26)
        Me.lbl_buscador.Name = "lbl_buscador"
        Me.lbl_buscador.Size = New System.Drawing.Size(56, 13)
        Me.lbl_buscador.TabIndex = 0
        Me.lbl_buscador.Text = "Localidad:"
        '
        'cmd_modificar
        '
        Me.cmd_modificar.Image = Global.MenuProyecto.My.Resources.Resources.Icono_modificar
        Me.cmd_modificar.Location = New System.Drawing.Point(129, 267)
        Me.cmd_modificar.Name = "cmd_modificar"
        Me.cmd_modificar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_modificar.TabIndex = 5
        Me.ToolTip1.SetToolTip(Me.cmd_modificar, "Modificar")
        Me.cmd_modificar.UseVisualStyleBackColor = True
        '
        'frm_Localidad
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(537, 324)
        Me.Controls.Add(Me.gbox_buscador)
        Me.Controls.Add(Me.cmd_modificar)
        Me.Controls.Add(Me.cmd_eliminar)
        Me.Controls.Add(Me.cmd_cancelar)
        Me.Controls.Add(Me.cmd_grabar)
        Me.Controls.Add(Me.cmd_nuevo)
        Me.Controls.Add(Me.cmd_salir)
        Me.Controls.Add(Me.grid_localidad)
        Me.Controls.Add(Me.cmb_provincia)
        Me.Controls.Add(Me.txt_nombre)
        Me.Controls.Add(Me.lbl_provincia)
        Me.Controls.Add(Me.lbl_nombre)
        Me.MaximizeBox = False
        Me.Name = "frm_Localidad"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Localidad"
        CType(Me.grid_localidad, System.ComponentModel.ISupportInitialize).EndInit()
        Me.gbox_buscador.ResumeLayout(False)
        Me.gbox_buscador.PerformLayout()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents cmd_cancelar As System.Windows.Forms.Button
    Friend WithEvents cmd_grabar As System.Windows.Forms.Button
    Friend WithEvents cmd_nuevo As System.Windows.Forms.Button
    Friend WithEvents cmd_salir As System.Windows.Forms.Button
    Friend WithEvents grid_localidad As System.Windows.Forms.DataGridView
    Friend WithEvents cmb_provincia As System.Windows.Forms.ComboBox
    Friend WithEvents txt_nombre As System.Windows.Forms.MaskedTextBox
    Friend WithEvents lbl_provincia As System.Windows.Forms.Label
    Friend WithEvents lbl_nombre As System.Windows.Forms.Label
    Friend WithEvents cmd_eliminar As System.Windows.Forms.Button
    Friend WithEvents gbox_buscador As System.Windows.Forms.GroupBox
    Friend WithEvents cmd_limpiar As System.Windows.Forms.Button
    Friend WithEvents txt_descripcion As System.Windows.Forms.MaskedTextBox
    Friend WithEvents lbl_buscador As System.Windows.Forms.Label
    Friend WithEvents id_localidad As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents n_localidad As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents n_provincia As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents cmd_modificar As System.Windows.Forms.Button
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
End Class
