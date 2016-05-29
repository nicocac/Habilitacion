Public Class frm_Estadisticas
    Dim acceso As New acceso_a_datos

    Sub cargar_reporte_G_Productos()
        Dim sql As String
        sql = "SELECT p.Nombre as Producto, sum(d.Precio*d.Cantidad) as Ganancias FROM Productos p INNER JOIN DetalleFactura d ON d.Id_Producto = p.Id_Producto GROUP BY p.Nombre "
        DS_G_Producto.DataSource = acceso._consulta(sql)

    End Sub

    Sub cargar_reporte_G_Clientes()
        Dim sql As String

        sql = "SELECT TOP 10 (c.Apellido + ', ' + c.Nombres) as Cliente, sum(d.Precio*d.Cantidad) as Ganancias FROM Cliente c INNER JOIN DetalleFactura d ON c.Nro_Doc = d.Nro_Doc GROUP BY (c.Apellido + ', ' + c.Nombres) ORDER BY sum(d.Precio*d.Cantidad) DESC "
        DS_G_Cliente.DataSource = acceso._consulta(sql)

    End Sub

    Sub cargar_reporte_U_Semestre()
        Dim sql As String

        sql = "SELECT TOP 6 MONTH(f.Fecha_Emision) as Mes, SUM(f.Total) as Ganancias FROM Factura f GROUP BY MONTH(f.Fecha_Emision)  ORDER BY 1 "
        DS_U_Semestre.DataSource = acceso._consulta(sql)

    End Sub

    Private Sub cmd_graficar_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_graficar_producto.Click
        cargar_reporte_G_Productos()
        Me.Reporte_G_Producto.RefreshReport()
    End Sub

    Private Sub cmd_graficar_cliente_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_graficar_cliente.Click
        cargar_reporte_G_Clientes()
        Me.Reporte_G_Cliente.RefreshReport()
    End Sub

    Private Sub cmd_graficar_u_semestre_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_graficar_u_semestre.Click
        cargar_reporte_U_Semestre()
        Me.Reporte_U_Semestre.RefreshReport()
    End Sub

    Private Sub frm_Estadisticas_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        cargar_reporte_G_Clientes()
        cargar_reporte_G_Productos()
        cargar_reporte_U_Semestre()

        Reporte_G_Cliente.RefreshReport()
        Reporte_G_Producto.RefreshReport()
        Reporte_U_Semestre.RefreshReport()

    End Sub

End Class