<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frm_Barrio
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(frm_Barrio))
        Me.grid_barrio = New System.Windows.Forms.DataGridView()
        Me.txt_nombre = New System.Windows.Forms.MaskedTextBox()
        Me.lbl_nombre = New System.Windows.Forms.Label()
        Me.cmb_localidad = New System.Windows.Forms.ComboBox()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.cmd_cargar_localidad = New System.Windows.Forms.Button()
        Me.gbox_buscador = New System.Windows.Forms.GroupBox()
        Me.cmd_limpiar = New System.Windows.Forms.Button()
        Me.txt_descripcion = New System.Windows.Forms.MaskedTextBox()
        Me.lbl_buscador = New System.Windows.Forms.Label()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.cmd_modificar = New System.Windows.Forms.Button()
        Me.cmd_eliminar = New System.Windows.Forms.Button()
        Me.cmd_limpiar_datos = New System.Windows.Forms.Button()
        Me.cmd_grabar = New System.Windows.Forms.Button()
        Me.cmd_nuevo = New System.Windows.Forms.Button()
        Me.cmd_salir = New System.Windows.Forms.Button()
        Me.cmb_Provincia = New System.Windows.Forms.ComboBox()
        Me.Label7 = New System.Windows.Forms.Label()
        Me.id_barrio = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.n_barrio = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.n_localidad = New System.Windows.Forms.DataGridViewTextBoxColumn()
        CType(Me.grid_barrio, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.gbox_buscador.SuspendLayout()
        Me.SuspendLayout()
        '
        'grid_barrio
        '
        Me.grid_barrio.AllowUserToAddRows = False
        Me.grid_barrio.AllowUserToDeleteRows = False
        Me.grid_barrio.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.grid_barrio.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.id_barrio, Me.n_barrio, Me.n_localidad})
        Me.grid_barrio.Cursor = System.Windows.Forms.Cursors.Arrow
        Me.grid_barrio.Location = New System.Drawing.Point(214, 12)
        Me.grid_barrio.Name = "grid_barrio"
        Me.grid_barrio.ReadOnly = True
        Me.grid_barrio.Size = New System.Drawing.Size(314, 178)
        Me.grid_barrio.TabIndex = 23
        '
        'txt_nombre
        '
        Me.txt_nombre.Cursor = System.Windows.Forms.Cursors.Arrow
        Me.txt_nombre.Location = New System.Drawing.Point(82, 23)
        Me.txt_nombre.Name = "txt_nombre"
        Me.txt_nombre.Size = New System.Drawing.Size(100, 20)
        Me.txt_nombre.TabIndex = 1
        '
        'lbl_nombre
        '
        Me.lbl_nombre.AutoSize = True
        Me.lbl_nombre.Cursor = System.Windows.Forms.Cursors.Arrow
        Me.lbl_nombre.Location = New System.Drawing.Point(29, 26)
        Me.lbl_nombre.Name = "lbl_nombre"
        Me.lbl_nombre.Size = New System.Drawing.Size(44, 13)
        Me.lbl_nombre.TabIndex = 20
        Me.lbl_nombre.Text = "Nombre"
        '
        'cmb_localidad
        '
        Me.cmb_localidad.Cursor = System.Windows.Forms.Cursors.Arrow
        Me.cmb_localidad.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_localidad.FormattingEnabled = True
        Me.cmb_localidad.Location = New System.Drawing.Point(82, 77)
        Me.cmb_localidad.Name = "cmb_localidad"
        Me.cmb_localidad.Size = New System.Drawing.Size(121, 21)
        Me.cmb_localidad.TabIndex = 2
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.BackColor = System.Drawing.SystemColors.ButtonFace
        Me.Label1.Cursor = System.Windows.Forms.Cursors.Arrow
        Me.Label1.Location = New System.Drawing.Point(20, 80)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(53, 13)
        Me.Label1.TabIndex = 36
        Me.Label1.Text = "Localidad"
        '
        'cmd_cargar_localidad
        '
        Me.cmd_cargar_localidad.Cursor = System.Windows.Forms.Cursors.Arrow
        Me.cmd_cargar_localidad.Location = New System.Drawing.Point(69, 114)
        Me.cmd_cargar_localidad.Name = "cmd_cargar_localidad"
        Me.cmd_cargar_localidad.Size = New System.Drawing.Size(134, 34)
        Me.cmd_cargar_localidad.TabIndex = 3
        Me.cmd_cargar_localidad.Text = "Cargar Localidad Nueva"
        Me.cmd_cargar_localidad.UseVisualStyleBackColor = True
        '
        'gbox_buscador
        '
        Me.gbox_buscador.Controls.Add(Me.cmd_limpiar)
        Me.gbox_buscador.Controls.Add(Me.txt_descripcion)
        Me.gbox_buscador.Controls.Add(Me.lbl_buscador)
        Me.gbox_buscador.Location = New System.Drawing.Point(243, 207)
        Me.gbox_buscador.Name = "gbox_buscador"
        Me.gbox_buscador.Size = New System.Drawing.Size(285, 59)
        Me.gbox_buscador.TabIndex = 0
        Me.gbox_buscador.TabStop = False
        Me.gbox_buscador.Text = "Buscador"
        '
        'cmd_limpiar
        '
        Me.cmd_limpiar.Image = Global.MenuProyecto.My.Resources.Resources.icono_x
        Me.cmd_limpiar.Location = New System.Drawing.Point(242, 18)
        Me.cmd_limpiar.Name = "cmd_limpiar"
        Me.cmd_limpiar.Size = New System.Drawing.Size(20, 20)
        Me.cmd_limpiar.TabIndex = 6
        Me.ToolTip1.SetToolTip(Me.cmd_limpiar, "Buscar")
        Me.cmd_limpiar.UseVisualStyleBackColor = True
        '
        'txt_descripcion
        '
        Me.txt_descripcion.Location = New System.Drawing.Point(56, 19)
        Me.txt_descripcion.Name = "txt_descripcion"
        Me.txt_descripcion.Size = New System.Drawing.Size(180, 20)
        Me.txt_descripcion.TabIndex = 1
        '
        'lbl_buscador
        '
        Me.lbl_buscador.AutoSize = True
        Me.lbl_buscador.Location = New System.Drawing.Point(13, 22)
        Me.lbl_buscador.Name = "lbl_buscador"
        Me.lbl_buscador.Size = New System.Drawing.Size(37, 13)
        Me.lbl_buscador.TabIndex = 0
        Me.lbl_buscador.Text = "Barrio:"
        '
        'cmd_modificar
        '
        Me.cmd_modificar.Image = Global.MenuProyecto.My.Resources.Resources.Icono_modificar
        Me.cmd_modificar.Location = New System.Drawing.Point(122, 284)
        Me.cmd_modificar.Name = "cmd_modificar"
        Me.cmd_modificar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_modificar.TabIndex = 6
        Me.ToolTip1.SetToolTip(Me.cmd_modificar, "Modificar")
        Me.cmd_modificar.UseVisualStyleBackColor = True
        '
        'cmd_eliminar
        '
        Me.cmd_eliminar.Image = Global.MenuProyecto.My.Resources.Resources.Icono_eliminar2
        Me.cmd_eliminar.Location = New System.Drawing.Point(173, 284)
        Me.cmd_eliminar.Name = "cmd_eliminar"
        Me.cmd_eliminar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_eliminar.TabIndex = 6
        Me.ToolTip1.SetToolTip(Me.cmd_eliminar, "Eliminar")
        Me.cmd_eliminar.UseVisualStyleBackColor = True
        '
        'cmd_limpiar_datos
        '
        Me.cmd_limpiar_datos.Image = CType(resources.GetObject("cmd_limpiar_datos.Image"), System.Drawing.Image)
        Me.cmd_limpiar_datos.Location = New System.Drawing.Point(220, 284)
        Me.cmd_limpiar_datos.Name = "cmd_limpiar_datos"
        Me.cmd_limpiar_datos.Size = New System.Drawing.Size(45, 45)
        Me.cmd_limpiar_datos.TabIndex = 7
        Me.ToolTip1.SetToolTip(Me.cmd_limpiar_datos, "Cancelar")
        Me.cmd_limpiar_datos.UseVisualStyleBackColor = True
        '
        'cmd_grabar
        '
        Me.cmd_grabar.Image = CType(resources.GetObject("cmd_grabar.Image"), System.Drawing.Image)
        Me.cmd_grabar.Location = New System.Drawing.Point(74, 284)
        Me.cmd_grabar.Name = "cmd_grabar"
        Me.cmd_grabar.Size = New System.Drawing.Size(45, 45)
        Me.cmd_grabar.TabIndex = 5
        Me.ToolTip1.SetToolTip(Me.cmd_grabar, "Guardar")
        Me.cmd_grabar.UseVisualStyleBackColor = True
        '
        'cmd_nuevo
        '
        Me.cmd_nuevo.Image = CType(resources.GetObject("cmd_nuevo.Image"), System.Drawing.Image)
        Me.cmd_nuevo.Location = New System.Drawing.Point(23, 284)
        Me.cmd_nuevo.Name = "cmd_nuevo"
        Me.cmd_nuevo.Size = New System.Drawing.Size(45, 45)
        Me.cmd_nuevo.TabIndex = 4
        Me.ToolTip1.SetToolTip(Me.cmd_nuevo, "Nuevo")
        Me.cmd_nuevo.UseVisualStyleBackColor = True
        '
        'cmd_salir
        '
        Me.cmd_salir.Image = Global.MenuProyecto.My.Resources.Resources.puerta
        Me.cmd_salir.Location = New System.Drawing.Point(483, 284)
        Me.cmd_salir.Name = "cmd_salir"
        Me.cmd_salir.Size = New System.Drawing.Size(45, 45)
        Me.cmd_salir.TabIndex = 9
        Me.ToolTip1.SetToolTip(Me.cmd_salir, "Salir")
        Me.cmd_salir.UseVisualStyleBackColor = True
        '
        'cmb_Provincia
        '
        Me.cmb_Provincia.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cmb_Provincia.FormattingEnabled = True
        Me.cmb_Provincia.Location = New System.Drawing.Point(82, 49)
        Me.cmb_Provincia.Name = "cmb_Provincia"
        Me.cmb_Provincia.Size = New System.Drawing.Size(121, 21)
        Me.cmb_Provincia.TabIndex = 37
        '
        'Label7
        '
        Me.Label7.AutoSize = True
        Me.Label7.Location = New System.Drawing.Point(25, 52)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(51, 13)
        Me.Label7.TabIndex = 38
        Me.Label7.Text = "Provincia"
        '
        'id_barrio
        '
        Me.id_barrio.HeaderText = "Id"
        Me.id_barrio.Name = "id_barrio"
        Me.id_barrio.ReadOnly = True
        Me.id_barrio.Width = 40
        '
        'n_barrio
        '
        Me.n_barrio.HeaderText = "Barrio"
        Me.n_barrio.Name = "n_barrio"
        Me.n_barrio.ReadOnly = True
        '
        'n_localidad
        '
        Me.n_localidad.HeaderText = "Localidad"
        Me.n_localidad.Name = "n_localidad"
        Me.n_localidad.ReadOnly = True
        '
        'frm_Barrio
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(540, 341)
        Me.Controls.Add(Me.cmb_Provincia)
        Me.Controls.Add(Me.Label7)
        Me.Controls.Add(Me.gbox_buscador)
        Me.Controls.Add(Me.cmd_cargar_localidad)
        Me.Controls.Add(Me.cmb_localidad)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.cmd_modificar)
        Me.Controls.Add(Me.cmd_eliminar)
        Me.Controls.Add(Me.cmd_limpiar_datos)
        Me.Controls.Add(Me.cmd_grabar)
        Me.Controls.Add(Me.cmd_nuevo)
        Me.Controls.Add(Me.cmd_salir)
        Me.Controls.Add(Me.grid_barrio)
        Me.Controls.Add(Me.txt_nombre)
        Me.Controls.Add(Me.lbl_nombre)
        Me.Cursor = System.Windows.Forms.Cursors.Arrow
        Me.MaximizeBox = False
        Me.Name = "frm_Barrio"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Barrio"
        CType(Me.grid_barrio, System.ComponentModel.ISupportInitialize).EndInit()
        Me.gbox_buscador.ResumeLayout(False)
        Me.gbox_buscador.PerformLayout()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents cmd_limpiar_datos As System.Windows.Forms.Button
    Friend WithEvents cmd_grabar As System.Windows.Forms.Button
    Friend WithEvents cmd_nuevo As System.Windows.Forms.Button
    Friend WithEvents cmd_salir As System.Windows.Forms.Button
    Friend WithEvents grid_barrio As System.Windows.Forms.DataGridView
    Friend WithEvents txt_nombre As System.Windows.Forms.MaskedTextBox
    Friend WithEvents lbl_nombre As System.Windows.Forms.Label
    Friend WithEvents cmb_localidad As System.Windows.Forms.ComboBox
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents cmd_cargar_localidad As System.Windows.Forms.Button
    Friend WithEvents cmd_eliminar As System.Windows.Forms.Button
    Friend WithEvents gbox_buscador As System.Windows.Forms.GroupBox
    Friend WithEvents txt_descripcion As System.Windows.Forms.MaskedTextBox
    Friend WithEvents lbl_buscador As System.Windows.Forms.Label
    Friend WithEvents cmd_limpiar As System.Windows.Forms.Button
    Friend WithEvents cmd_modificar As System.Windows.Forms.Button
    Friend WithEvents ToolTip1 As System.Windows.Forms.ToolTip
    Friend WithEvents cmb_Provincia As System.Windows.Forms.ComboBox
    Friend WithEvents Label7 As System.Windows.Forms.Label
    Friend WithEvents id_barrio As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents n_barrio As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents n_localidad As System.Windows.Forms.DataGridViewTextBoxColumn
End Class
