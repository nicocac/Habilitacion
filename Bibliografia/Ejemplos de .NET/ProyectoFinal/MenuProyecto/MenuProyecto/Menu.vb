Public Class frm_elcampito

    Private Sub btm_salir_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cmd_salir.Click
        Me.Close()
    End Sub

    Private Sub frm_elcampito_FormClosing(ByVal sender As Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles Me.FormClosing
        If MessageBox.Show("¿Esta seguro que desea salir?", "Importante", _
              MessageBoxButtons.YesNo, MessageBoxIcon.Question) _
          = Windows.Forms.DialogResult.Yes Then
            e.Cancel = False
        Else
            e.Cancel = True
        End If
    End Sub


    Private Sub frm_elcampito_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub


    Private Sub ReportesToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub

    Private Sub item_producto_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles item_producto.Click
        frm_producto.Show()

    End Sub

    Private Sub item_cliente_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles item_cliente.Click
        frm_Cliente.Show()
    End Sub

    Private Sub item_proovedor_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles item_proovedor.Click
        frm_proveedores.Show()
    End Sub

    Private Sub item_localidad_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles item_localidad.Click
        frm_Localidad.ShowDialog()
    End Sub

    Private Sub item_barrio_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles item_barrio.Click
        frm_Barrio.ShowDialog()

    End Sub

    Private Sub item_venta_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles item_venta.Click
        frm_Factura.ShowDialog()
    End Sub

    Private Sub ListadosToolStripMenuItem_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ListadosToolStripMenuItem.Click
        frm_Listados.Show()
    End Sub

    Private Sub item_compra_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles item_compra.Click
        frm_Compra.ShowDialog()

    End Sub

    Private Sub EstadísticasToolStripMenuItem1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles EstadísticasToolStripMenuItem1.Click
        frm_Estadisticas.Show()
    End Sub
End Class
