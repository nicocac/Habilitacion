Public Class frm_Listados

    Dim string_conexion As String = "Data Source=CONSTANZA-PC\Sqlexpress;Initial Catalog=ElCampito;Integrated Security=True"

    Private Sub Listados_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

        cargar_productos()
        cargar_clientes()
        cargar_proveedores()
        cmb_meses.SelectedIndex = -1

        Me.ReportClientes.RefreshReport()
        'Me.ReportFacturas.RefreshReport()
        Me.ReportProductos1.RefreshReport()
        Me.ReportProveedores.RefreshReport()
    End Sub

    Private Sub cargar_productos()
        Dim conexion As New Data.SqlClient.SqlConnection
        Dim cmd As New Data.SqlClient.SqlCommand
        Dim tabla As New Data.DataTable

        conexion.ConnectionString = string_conexion
        conexion.Open()
        cmd.Connection = conexion
        cmd.CommandType = CommandType.Text
        cmd.CommandText = "SELECT * FROM Productos"
        tabla.Load(cmd.ExecuteReader())

        Me.DS_LP.DataSource = tabla
    End Sub

    Private Sub cmd_buscar_producto_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_buscar_producto.Click
        buscar_productos()
    End Sub

    Private Sub buscar_productos()
        Dim conexion As New Data.SqlClient.SqlConnection
        Dim cmd As New Data.SqlClient.SqlCommand
        Dim tabla As New Data.DataTable

        conexion.ConnectionString = string_conexion
        conexion.Open()
        cmd.Connection = conexion
        cmd.CommandType = CommandType.Text
        cmd.CommandText = "SELECT * FROM Productos WHERE Nombre LIKE '%" & Me.txt_buscar_producto.Text.Trim & "%'"
        tabla.Load(cmd.ExecuteReader())

        Me.DS_LP.DataSource = tabla

        Me.ReportProductos1.RefreshReport() 'ESTO SE PONE PORQUE DESPUES DE BUSCAR, DEBE REFRESCAR!

    End Sub

    Private Sub cargar_clientes()
        Dim conexion As New Data.SqlClient.SqlConnection
        Dim cmd As New Data.SqlClient.SqlCommand
        Dim tabla As New Data.DataTable

        conexion.ConnectionString = string_conexion
        conexion.Open()
        cmd.Connection = conexion
        cmd.CommandType = CommandType.Text
        cmd.CommandText = "SELECT * FROM Cliente ORDER BY Apellido, Nombres"
        tabla.Load(cmd.ExecuteReader())

        Me.DS_LC.DataSource = tabla

    End Sub

    Private Sub cmd_buscar_cliente_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_buscar_cliente.Click
        buscar_clientes()
    End Sub

    Private Sub buscar_clientes()
        Dim conexion As New Data.SqlClient.SqlConnection
        Dim cmd As New Data.SqlClient.SqlCommand
        Dim tabla As New Data.DataTable

        conexion.ConnectionString = string_conexion
        conexion.Open()
        cmd.Connection = conexion
        cmd.CommandType = CommandType.Text
        cmd.CommandText = "SELECT * FROM Cliente WHERE Nro_Doc LIKE '%" & Me.txt_buscar_cliente.Text.Trim & "%'"
        tabla.Load(cmd.ExecuteReader())

        Me.DS_LC.DataSource = tabla

        Me.ReportClientes.RefreshReport() ' SE PONE PARA REFRESCAR EN EL MISMO REPORTE

    End Sub

    Private Sub cmd_buscar_factura_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_buscar_factura.Click
        buscar_facturas()
    End Sub

    Private Sub buscar_facturas()
        Dim conexion As New Data.SqlClient.SqlConnection
        Dim cmd As New Data.SqlClient.SqlCommand
        Dim tabla As New Data.DataTable

        conexion.ConnectionString = string_conexion
        conexion.Open()
        cmd.Connection = conexion
        cmd.CommandType = CommandType.Text
        cmd.CommandText = "SELECT Factura.Nro_Fact, Factura.Fecha_Emision, Cliente.Apellido, Cliente.Nombres, Factura.Total"
        cmd.CommandText &= " FROM Cliente INNER JOIN Factura ON Cliente.Tipo_Doc = Factura.Tipo_Doc AND Cliente.Nro_Doc = Factura.Nro_Doc"
        cmd.CommandText &= " WHERE MONTH(Fecha_Emision) = " & (cmb_meses.SelectedIndex + 1)

        tabla.Load(cmd.ExecuteReader())

        Me.DS_LF.DataSource = tabla

        ReportFacturas.RefreshReport()
    End Sub

    Private Sub cargar_proveedores()
        Dim conexion As New Data.SqlClient.SqlConnection
        Dim cmd As New Data.SqlClient.SqlCommand
        Dim tabla As New Data.DataTable

        conexion.ConnectionString = string_conexion
        conexion.Open()
        cmd.Connection = conexion
        cmd.CommandType = CommandType.Text
        cmd.CommandText = "SELECT * FROM Proveedores ORDER BY Razon_Social"

        tabla.Load(cmd.ExecuteReader())

        Me.DS_LPRO.DataSource = tabla

        ReportProveedores.RefreshReport()
    End Sub

    Private Sub cmd_buscar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_buscar.Click
        buscar_proveedores()
    End Sub

    Private Sub buscar_proveedores()
        Dim conexion As New Data.SqlClient.SqlConnection
        Dim cmd As New Data.SqlClient.SqlCommand
        Dim tabla As New Data.DataTable

        conexion.ConnectionString = string_conexion
        conexion.Open()
        cmd.Connection = conexion
        cmd.CommandType = CommandType.Text
        cmd.CommandText = "SELECT * FROM Proveedores WHERE Razon_Social LIKE '%" & txt_buscar_proveedor.Text.Trim & "%'"

        tabla.Load(cmd.ExecuteReader)

        Me.DS_LPRO.DataSource = tabla

        ReportProveedores.RefreshReport()
    End Sub

    Private Sub cmd_grafico_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_grafico.Click
        Listado_Productos_Grafico.Show()
    End Sub
End Class