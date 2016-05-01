Public Class frm_ProveedoresxProducto
    Dim bd As New acceso_a_datos
    Enum estado
        insertar
    End Enum
    Enum termino
        aprobado
        rechazado
    End Enum
    Dim accion As estado = estado.insertar

    Private Sub frm_ProveedoresxProducto_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        bd._tabla = "Productos"
        Dim tablaProductos As DataTable = bd.leo_tabla
        cargar_combo(cmb_producto, tablaProductos, "Id_Producto", "Nombre")

        bd._tabla = "Proveedores"
        Dim tablaProveedores As DataTable = bd.leo_tabla
        cargar_combo(cmb_proveedor, tablaProveedores, "Id_Proveedores", "Razon_Social")

        cmd_grabar.Enabled = True
        limpiar()

    End Sub

    Private Sub cargar_combo(ByRef combo As ComboBox, ByRef datos As Data.DataTable, ByVal pk As String, ByVal descripcion As String)

        combo.DataSource = datos
        combo.ValueMember = pk
        combo.DisplayMember = descripcion
    End Sub

    Private Sub cmd_grabar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_grabar.Click
        If validar() = True Then
            If accion = estado.insertar Then
                If validar_existencia() = termino.aprobado Then
                    Dim sql As String
                    sql = "INSERT INTO ProveedoresXProducto(Id_Producto, Id_Proveedor) "
                    sql &= "VALUES (" & Me.cmb_producto.SelectedValue & ", " & Me.cmb_proveedor.SelectedValue & ")"
                    bd._consulta(sql)
                    MessageBox.Show("Se grabó exitosamente", "Importante", MessageBoxButtons.OK, MessageBoxIcon.Information)
                    limpiar()

                Else
                    MsgBox("Ya existe este proveedor para este producto, seleccione otro", MsgBoxStyle.Information, "Importante")
                    cmb_proveedor.SelectedIndex = -1
                    cmb_proveedor.Focus()
                    Return

                End If
            End If
        End If

    End Sub

    Private Sub cmd_salir_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_salir.Click
        Me.Close()
    End Sub

    Private Function validar_existencia() As termino
        Dim tabla As New Data.DataTable
        Dim sql As String

        sql = "SELECT * FROM ProveedoresXProducto"
        sql &= " WHERE Id_Proveedor =  '" & Me.cmb_proveedor.SelectedValue & "' and Id_Producto = '" & Me.cmb_producto.SelectedValue & "'"
        tabla = bd._consulta(sql)

        If tabla.Rows.Count = 1 Then
            Return termino.rechazado
        Else
            Return termino.aprobado

        End If
    End Function

    'Metodos de deshabilitar, habilitar y limpiar

    Private Sub limpiar()
        Me.cmb_producto.SelectedIndex = -1
        Me.cmb_proveedor.SelectedIndex = -1
        Me.cmb_producto.Focus()
    End Sub

    Private Sub habilitar()
        Me.cmb_producto.Enabled = True
        Me.cmb_proveedor.Enabled = True
    End Sub

    'Metodo de validación de campos
    Private Function validar() As Boolean
        If cmb_proveedor.SelectedIndex = -1 Then
            MsgBox("Falta seleccionar el proveedor", MsgBoxStyle.Critical, "Importante")
            Me.cmb_proveedor.Focus()
            Return False
            Exit Function
        End If

        If cmb_producto.SelectedIndex = -1 Then
            MsgBox("Falta seleccionar el producto", MsgBoxStyle.Critical, "Importante")
            Me.cmb_producto.Focus()
            Return False
            Exit Function
        End If

        Return True
    End Function


End Class