Public Class Listado_Productos_Grafico
    Dim string_conexion As String = "Data Source=CONSTANZA-PC\Sqlexpress;Initial Catalog=ElCampito;Integrated Security=True"
    Private Sub Listado_Productos_Grafico_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        cargar_datos()
        Me.ReportViewer1.RefreshReport()
    End Sub

    Private Sub cargar_datos()
        Dim conexion As New Data.SqlClient.SqlConnection
        Dim cmd As New Data.SqlClient.SqlCommand
        Dim tabla As New Data.DataTable

        conexion.ConnectionString = string_conexion
        conexion.Open()
        cmd.Connection = conexion
        cmd.CommandType = CommandType.Text
        cmd.CommandText = "SELECT * FROM Productos"
        tabla.Load(cmd.ExecuteReader())

        Me.DS_Productos_grafico.DataSource = tabla
        Me.ReportViewer1.RefreshReport()
    End Sub
End Class