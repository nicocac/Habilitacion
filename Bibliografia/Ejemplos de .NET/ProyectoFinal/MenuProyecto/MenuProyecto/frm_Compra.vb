Public Class frm_Compra
    Dim bd As New acceso_a_datos
    Enum estado
        insertar
    End Enum
    Dim accion As estado = estado.insertar

    Private Sub frm_Compra_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        bd._tabla = "Productos"
        Dim tablaProductos As DataTable = bd.leo_tabla
        cargar_combo(cmb_producto, tablaProductos, "Id_Producto", "Nombre")

        Me.deshabilitar()
        cmd_nuevo.Enabled = True
        cmd_grabar.Enabled = False
        cmd_cancelar.Enabled = False
        cargarGrilla()
        limpiar()
    End Sub

    Private Sub frm_proveedores_FormClosing(ByVal sender As Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles Me.FormClosing
        If MessageBox.Show("¿Esta seguro que desea salir?", "Importante", _
              MessageBoxButtons.YesNo, MessageBoxIcon.Question) _
          = Windows.Forms.DialogResult.Yes Then
            e.Cancel = False
        Else
            e.Cancel = True
        End If
    End Sub

    Private Sub cargar_combo(ByRef combo As ComboBox, ByRef datos As Data.DataTable, ByVal pk As String, ByVal descripcion As String)
        combo.DataSource = datos
        combo.ValueMember = pk
        combo.DisplayMember = descripcion
    End Sub

    Private Sub DataGridView1_CellContentClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles grid1.CellContentClick

    End Sub

    Private Sub cmd_nuevo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_nuevo.Click
        limpiar()
        habilitar()
        Me.cmb_proveedor.Enabled = False
        Me.cmd_grabar.Enabled = True
        Me.cmd_cancelar.Enabled = True
        Me.cmd_nuevo.Enabled = False
        Me.accion = estado.insertar
    End Sub

    'Metodo de validación de campos
    Private Function validar() As Boolean
       

        If cmb_producto.SelectedIndex = -1 Then
            MsgBox("Falta seleccionar el producto", MsgBoxStyle.Critical, "Importante")
            Me.cmb_producto.Focus()
            Return False
            Exit Function
        End If

        If cmb_proveedor.SelectedIndex = -1 Then
            MsgBox("Falta seleccionar el proveedor", MsgBoxStyle.Critical, "Importante")
            Me.cmb_proveedor.Focus()
            Return False
            Exit Function
        End If
        If txt_cantidad.Text.Trim = "" Then
            MsgBox("Falta ingresar la cantidad", MsgBoxStyle.Critical, "Importante")
            Me.txt_cantidad.Focus()
            Return False
            Exit Function
        End If

        Return True
    End Function

    'Validación de campo numerico
    Private Sub numeros_KeyPress(ByVal sender As Object, ByVal e As System.Windows.Forms.KeyPressEventArgs) Handles txt_cantidad.KeyPress
        Select Case Asc(e.KeyChar)
            Case 4, 24, 19, 127, 13, 9, 15, 14, 8
                Exit Sub
        End Select
        If IsNumeric(e.KeyChar) = False Then
            MsgBox("Este caracter no es un número (" + e.KeyChar + ")", vbCritical, "Importante")
            e.KeyChar = ""
        End If
    End Sub

    'Metodos de deshabilitar, habilitar y limpiar
    Private Sub deshabilitar()
        Me.cmb_producto.Enabled = False
        Me.cmb_proveedor.Enabled = False
        Me.txt_cantidad.Enabled = False
    End Sub

    Private Sub limpiar()
        Me.cmb_producto.SelectedIndex = -1
        Me.cmb_proveedor.SelectedIndex = -1
        Me.txt_cantidad.Text = ""
        Me.cmb_producto.Focus()
    End Sub

    Private Sub habilitar()
        Me.cmb_producto.Enabled = True
        Me.cmb_proveedor.Enabled = True
        Me.txt_cantidad.Enabled = True
    End Sub

    Private Sub cmd_salir_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_salir.Click
        Me.Close()
    End Sub

    Private Sub cmd_grabar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_grabar.Click
        If validar() = True Then
            If accion = estado.insertar Then
                Dim sql As String
                sql = "UPDATE Productos set Cant_Stock = Cant_Stock + '" & Me.txt_cantidad.Text & "'"
                sql &= " WHERE (Id_Producto = " & Me.cmb_producto.SelectedValue & ")"
                bd._consulta(sql)

                MessageBox.Show("Se grabó exitosamente", "Importante", MessageBoxButtons.OK, MessageBoxIcon.Information)
                cmd_grabar.Enabled = False
                cargarGrilla()
                limpiar()
                deshabilitar()
                cmd_cancelar.Enabled = False
                cmd_nuevo.Enabled = True
            End If
        End If

    End Sub

    Private Sub cmb_producto_SelectionChangeCommitted(ByVal sender As Object, ByVal e As System.EventArgs) Handles cmb_producto.SelectionChangeCommitted
        'bd._tabla = "ProveedoresXProducto"
        'Dim tablaProveedoresXProducto As DataTable = bd.leo_tabla("Id_Producto =" & cmb_producto.SelectedValue)
        Dim sql As String
        Dim tabla1 As DataTable

        sql = "SELECT P.Razon_Social, PP.Id_Proveedor FROM ProveedoresXProducto PP INNER JOIN Proveedores P on PP.Id_Proveedor = P.Id_Proveedores"
        sql &= " WHERE PP.Id_Producto = " & Me.cmb_producto.SelectedValue & ""
        tabla1 = bd._consulta(sql)

        cargar_combo(cmb_proveedor, tabla1, "Id_Proveedor", "Razon_Social")
        cmb_proveedor.Enabled = True
        cmb_proveedor.SelectedIndex = -1

    End Sub
    Private Sub leo_tabla_combo()

    End Sub
    Private Sub cargarGrilla()
        Dim sql As String
        sql = "SELECT Nombre, Cant_Stock"
        sql &= " FROM Productos"
        Me.grid1.Rows.Clear()
        Dim tabla As DataTable = bd._consulta(sql)

        For c = 0 To tabla.Rows.Count() - 1
            Me.grid1.Rows.Add(tabla.Rows(c)("Nombre"), tabla.Rows(c)("Cant_Stock"))
        Next
    End Sub

   
    Private Sub cmd_asignar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_asignar.Click
        frm_ProveedoresxProducto.ShowDialog()
        limpiar()
        deshabilitar()
        cmd_nuevo.Enabled = True
        cmd_grabar.Enabled = False
        cmd_cancelar.Enabled = False

    End Sub

    Private Sub cmd_cancelar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_cancelar.Click
        If MessageBox.Show("¿Esta seguro que desea cancelar?", "Atención", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button1) = Windows.Forms.DialogResult.OK Then
            limpiar()
            deshabilitar()
            cargarGrilla()
            Me.cmd_grabar.Enabled = False
            Me.cmd_nuevo.Enabled = True
        End If
    End Sub
End Class