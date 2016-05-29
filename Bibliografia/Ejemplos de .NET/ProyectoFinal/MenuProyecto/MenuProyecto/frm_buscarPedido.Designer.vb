<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frm_buscarPedido
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
        Dim DataGridViewCellStyle1 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle2 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle3 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle4 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Dim DataGridViewCellStyle5 As System.Windows.Forms.DataGridViewCellStyle = New System.Windows.Forms.DataGridViewCellStyle()
        Me._grid1 = New System.Windows.Forms.DataGridView()
        Me.Nombre = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Nro_Doc = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Tipo_Fact = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Nro_Fact = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Fecha_Emision = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.total = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.cmd_salir = New System.Windows.Forms.Button()
        CType(Me._grid1, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        '_grid1
        '
        Me._grid1.AllowUserToAddRows = False
        DataGridViewCellStyle1.BackColor = System.Drawing.Color.FromArgb(CType(CType(255, Byte), Integer), CType(CType(224, Byte), Integer), CType(CType(192, Byte), Integer))
        Me._grid1.AlternatingRowsDefaultCellStyle = DataGridViewCellStyle1
        Me._grid1.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D
        Me._grid1.ColumnHeadersBorderStyle = System.Windows.Forms.DataGridViewHeaderBorderStyle.[Single]
        Me._grid1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me._grid1.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.Nombre, Me.Nro_Doc, Me.Tipo_Fact, Me.Nro_Fact, Me.Fecha_Emision, Me.total})
        Me._grid1.Location = New System.Drawing.Point(12, 12)
        Me._grid1.Name = "_grid1"
        Me._grid1.RowHeadersBorderStyle = System.Windows.Forms.DataGridViewHeaderBorderStyle.[Single]
        Me._grid1.RowHeadersWidth = 30
        Me._grid1.Size = New System.Drawing.Size(576, 213)
        Me._grid1.TabIndex = 1
        '
        'Nombre
        '
        DataGridViewCellStyle2.Format = "N0"
        DataGridViewCellStyle2.NullValue = Nothing
        Me.Nombre.DefaultCellStyle = DataGridViewCellStyle2
        Me.Nombre.HeaderText = "Tipo Documento"
        Me.Nombre.Name = "Nombre"
        Me.Nombre.Width = 70
        '
        'Nro_Doc
        '
        DataGridViewCellStyle3.Format = "N0"
        DataGridViewCellStyle3.NullValue = Nothing
        Me.Nro_Doc.DefaultCellStyle = DataGridViewCellStyle3
        Me.Nro_Doc.HeaderText = "Nro Documento"
        Me.Nro_Doc.Name = "Nro_Doc"
        Me.Nro_Doc.Width = 70
        '
        'Tipo_Fact
        '
        Me.Tipo_Fact.HeaderText = "Tipo Factura"
        Me.Tipo_Fact.Name = "Tipo_Fact"
        Me.Tipo_Fact.Width = 70
        '
        'Nro_Fact
        '
        Me.Nro_Fact.HeaderText = "Nro Factura"
        Me.Nro_Fact.Name = "Nro_Fact"
        '
        'Fecha_Emision
        '
        DataGridViewCellStyle4.Format = "d"
        DataGridViewCellStyle4.NullValue = Nothing
        Me.Fecha_Emision.DefaultCellStyle = DataGridViewCellStyle4
        Me.Fecha_Emision.HeaderText = "Fecha"
        Me.Fecha_Emision.Name = "Fecha_Emision"
        Me.Fecha_Emision.Width = 70
        '
        'total
        '
        DataGridViewCellStyle5.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleRight
        DataGridViewCellStyle5.Format = "N2"
        DataGridViewCellStyle5.NullValue = Nothing
        Me.total.DefaultCellStyle = DataGridViewCellStyle5
        Me.total.HeaderText = "Total"
        Me.total.Name = "total"
        '
        'cmd_salir
        '
        Me.cmd_salir.Image = Global.MenuProyecto.My.Resources.Resources.puerta
        Me.cmd_salir.Location = New System.Drawing.Point(556, 247)
        Me.cmd_salir.Name = "cmd_salir"
        Me.cmd_salir.Size = New System.Drawing.Size(45, 45)
        Me.cmd_salir.TabIndex = 10
        Me.cmd_salir.UseVisualStyleBackColor = True
        '
        'frm_buscarPedido
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(613, 304)
        Me.Controls.Add(Me.cmd_salir)
        Me.Controls.Add(Me._grid1)
        Me.Name = "frm_buscarPedido"
        Me.Text = "Buscar Pedido"
        CType(Me._grid1, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents _grid1 As System.Windows.Forms.DataGridView
    Friend WithEvents cmd_salir As System.Windows.Forms.Button
    Friend WithEvents Nombre As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Nro_Doc As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Tipo_Fact As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Nro_Fact As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents Fecha_Emision As System.Windows.Forms.DataGridViewTextBoxColumn
    Friend WithEvents total As System.Windows.Forms.DataGridViewTextBoxColumn
End Class
