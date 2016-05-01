Public Class acceso_a_datos

    Enum tipo_conexion
        transaccion
        simple
    End Enum
    Enum motores
        oracle
        sqlserver
        OleDb
    End Enum
    Enum estado_transaccion
        _ok
        _error
    End Enum

    'Provider=Microsoft.Jet.OLEDB.4.0;Data Source=G:\utn\pav1_VB.NET\clases\tp04_abm_alumnos\DBF_ABM_alumno_personas.mdb
    ' Data Source=KIKE\SQLEXPRESS;Initial Catalog="E:\DROPBOX\PAV\ULTIMAVERSION\BASE DE DATOS\ELCAMPITO.MDF";Integrated Security=True


    Dim configurar_conexion As tipo_conexion = tipo_conexion.simple
    Dim transaccion As New Object
    Dim conexion As New Object
    Dim cmd As New Object
    Dim control_transaccion As estado_transaccion = estado_transaccion._ok
    Dim motor As motores = motores.sqlserver
    Dim sting_conexion As String = "Data Source=CONSTANZA-PC\Sqlexpress;Initial Catalog=ElCampito;Integrated Security=True"
    Dim tabla As String = ""

    Public Property _CadenaConexion() As String
        Get
            Return Me.sting_conexion
        End Get
        Set(ByVal value As String)
            Me.sting_conexion = value
        End Set
    End Property
    Public Property _tabla() As String
        Get
            Return Me.tabla
        End Get
        Set(ByVal value As String)
            Me.tabla = value
        End Set
    End Property
    Public Sub New()
        Select Case Me.motor
            Case motores.OleDb
                conexion = New Data.OleDb.OleDbConnection
                cmd = New Data.OleDb.OleDbCommand
            Case motores.sqlserver
                conexion = New Data.SqlClient.SqlConnection
                cmd = New Data.SqlClient.SqlCommand
        End Select
    End Sub
    Public Sub New(ByVal _CadenaConexion As String, ByVal _MotorBaseDatos As motores)
        Me.sting_conexion = _CadenaConexion
        Me.motor = _MotorBaseDatos

        Select Case Me.motor
            Case motores.OleDb
                conexion = New Data.OleDb.OleDbConnection
                cmd = New Data.OleDb.OleDbCommand
            Case motores.sqlserver
                conexion = New Data.SqlClient.SqlConnection
                cmd = New Data.SqlClient.SqlCommand
        End Select
    End Sub
    Public Sub _iniciar_conexion_con_transaccion()
        Me.configurar_conexion = tipo_conexion.transaccion
        Me.control_transaccion = estado_transaccion._ok
    End Sub
    Private Sub _conectar()
        If Me.conexion.State.ToString() <> "Open" Then
            Me.conexion.ConnectionString = Me.sting_conexion
            Me.conexion.Open()
            Me.cmd.Connection = conexion
            Me.cmd.CommandType = CommandType.Text

            If Me.configurar_conexion = tipo_conexion.transaccion Then
                Me.transaccion = Me.conexion.BeginTransaction()
                Me.cmd.Transaction = Me.transaccion
            End If
        End If
    End Sub
    Public Function _finalizar_conexio_con_transaccion() As estado_transaccion
        If Me.control_transaccion = estado_transaccion._ok Then
            Me.transaccion.Commit()
        Else
            Me.transaccion.Rollback()
        End If

        Me.conexion.Close()
        Me.configurar_conexion = tipo_conexion.simple

        Return Me.control_transaccion

    End Function
    Public Function leo_tabla() As Data.DataTable
        Return Me._consulta("select * from " & Me.tabla)
    End Function
    Public Function leo_tabla(ByVal pk As String, ByVal descripcion As String) As Data.DataTable
        Return Me._consulta("select " & pk & ", " & descripcion & " from " & Me.tabla)
    End Function
    Public Function leo_tabla(ByVal restriccion As String) As Data.DataTable
        Return Me._consulta("select * from " & Me.tabla & " Where " & restriccion)
    End Function
    Public Function _consulta(ByVal comando As String) As Data.DataTable
        Dim _tabla As New Data.DataTable
        Me._conectar()
        Me.cmd.CommandText = comando
        Try
            _tabla.Load(Me.cmd.ExecuteReader())
        Catch ex As Exception
            MessageBox.Show(ex.Message, "Error en consulta")
        End Try

        If Me.configurar_conexion = tipo_conexion.simple Then
            Me.conexion.Close()
        End If
        Return _tabla

    End Function
    Public Sub _consulta(ByVal comando As String, ByRef _tabla As Data.DataTable)
        Me._conectar()
        Me.cmd.CommandText = comando
        Try
            _tabla.Load(Me.cmd.ExecuteReader())
        Catch ex As Exception
            MessageBox.Show(ex.Message, "Error en consulta")
        End Try
        If Me.configurar_conexion = tipo_conexion.simple Then
            Me.conexion.Close()
        End If
    End Sub
    Public Sub _borrar(ByVal comando As String)
        Me._ejecutar(comando)
        If Me.configurar_conexion = tipo_conexion.simple Then
            Me.conexion.Close()
        End If
    End Sub
    Private Function _ejecutar(ByVal comando As String) As String
        Me._conectar()
        Me.cmd.CommandText = comando
        Try
            cmd.ExecuteNonQuery()
            Return "ok"
        Catch ex As Exception
            MessageBox.Show(ex.Message)
            Me.control_transaccion = estado_transaccion._error
            Return ex.Message
        End Try
    End Function
    Public Sub _modificar(ByVal comando As String)
        Me._ejecutar(comando)
        If Me.configurar_conexion = tipo_conexion.simple Then
            Me.conexion.Close()
        End If
    End Sub

    Public Function _modificar(ByVal valores As String, ByVal restriccion As String) As String
        Dim _update As String = "UPDATE " + Me.tabla + " SET " + Chr(13)
        Dim _set As String
        Dim _restriccion As String

        _set = Me._arma_set_update(valores)
        If _set = "error" Then
            Return "error"
        End If

        _restriccion = _armar_restriccion_update(restriccion)

        If _restriccion = "error" Then
            Return "error"
        End If

        _update &= _set + _restriccion

        'MsgBox(_update)

        Dim estado As String = Me._ejecutar(_update)
        If Me.configurar_conexion = tipo_conexion.transaccion Then
            If estado = "ok" Then
                Return "ok"
            Else
                Return "error"
            End If
        Else
            Me.conexion.Close()
            Return ("ok")
        End If

    End Function
    Private Function _armar_restriccion_update(ByVal restriccion As String) As String
        Dim objeto_tabla As Data.DataTable     'tabla que contiene la estructura en la base
        Dim contador As Integer = 0             'contador de bucle
        Dim c As Integer = 0                    'para recorrer las columas buscando una
        Dim coma As Integer = 0                 'ubicacion de la "," 
        Dim igual As Integer = 0                'ubicación del "="
        Dim txt_origen As String = ""           'texto origen 
        Dim nombre_campo As String = ""         'campo a buscar 
        Dim valor As String = ""                'valor del campo 
        Dim txt As String = ""                  'texto que se va formando para el insert
        Dim tipo_dato As String = ""
        Dim estado As Boolean = True
        Dim encontro As Boolean = False
        Dim primera_vez As Boolean = True

        objeto_tabla = Me.leo_estructura()
        txt_origen = restriccion.ToUpper.Trim()

        While estado
            igual = txt_origen.IndexOf("=")
            coma = IIf(txt_origen.IndexOf(",") = -1, txt_origen.Length, txt_origen.IndexOf(","))
            If txt_origen.IndexOf(",") = -1 Then
                estado = False
            End If
            nombre_campo = txt_origen.Substring(0, igual).Trim()
            valor = txt_origen.Substring(igual + 1, coma - igual - 1).Trim()
            tipo_dato = objeto_tabla.Columns(contador).DataType.ToString

            For c = 0 To objeto_tabla.Columns.Count() - 1
                If nombre_campo.Trim() = objeto_tabla.Columns(c).Caption.ToUpper.Trim() Then
                    tipo_dato = objeto_tabla.Columns(c).DataType.ToString
                    encontro = True
                End If
            Next
            If encontro Then
                If primera_vez Then
                    txt &= "WHERE " + nombre_campo + " = " + acomodo_al_tipo_dato(valor, tipo_dato) + Chr(13)
                    primera_vez = False
                Else
                    txt &= " AND " + nombre_campo + " = " + acomodo_al_tipo_dato(valor, tipo_dato) + Chr(13)
                End If
            Else
                MsgBox("no existe el nombre de columna " + nombre_campo, MsgBoxStyle.Critical, "Importante")
                txt = "error"
                Exit While
            End If
            If estado = True Then
                txt_origen = txt_origen.Substring(coma + 1)
            End If

        End While
        Return txt

    End Function
    Private Function _arma_set_update(ByVal valores As String) As String
        Dim objeto_tabla As Data.DataTable     'tabla que contiene la estructura en la base
        Dim contador As Integer = 0             'contador de bucle
        Dim c As Integer = 0                    'para recorrer las columas buscando una
        Dim coma As Integer = 0                 'ubicacion de la "," 
        Dim igual As Integer = 0                'ubicación del "="
        Dim txt_origen As String = ""           'texto origen 
        Dim nombre_campo As String = ""         'campo a buscar 
        Dim valor As String = ""                'valor del campo 
        Dim txt As String = ""                  'texto que se va formando para el insert
        Dim tipo_dato As String = ""
        Dim estado As Boolean = True
        Dim encontro As Boolean = False
        Dim primera_vez As Boolean = True

        objeto_tabla = Me.leo_estructura()
        txt_origen = valores.Trim()

        While estado
            igual = txt_origen.IndexOf("=")
            coma = IIf(txt_origen.IndexOf(",") = -1, txt_origen.Length, txt_origen.IndexOf(","))
            If txt_origen.IndexOf(",") = -1 Then
                estado = False
            End If
            nombre_campo = txt_origen.Substring(0, igual).Trim
            valor = txt_origen.Substring(igual + 1, coma - igual - 1).Trim()
            tipo_dato = objeto_tabla.Columns(contador).DataType.ToString

            For c = 0 To objeto_tabla.Columns.Count() - 1
                If nombre_campo.Trim.ToUpper() = objeto_tabla.Columns(c).Caption.ToUpper.Trim() Then
                    tipo_dato = objeto_tabla.Columns(c).DataType.ToString
                    encontro = True
                End If
            Next
            If encontro Then
                If primera_vez Then
                    txt &= nombre_campo + " = " + acomodo_al_tipo_dato(valor, tipo_dato) + Chr(13)
                    primera_vez = False
                Else
                    txt &= ", " + nombre_campo + " = " + acomodo_al_tipo_dato(valor, tipo_dato) + Chr(13)
                End If
            Else
                MsgBox("no existe el nombre de columna " + nombre_campo, MsgBoxStyle.Critical, "Importante")
                txt = "error"
                Exit While
            End If
            If estado = True Then
                txt_origen = txt_origen.Substring(coma + 1)
            End If

        End While
        Return txt
    End Function

    Public Function _insertar(ByVal valores As String) As String
        Dim txt_Insert As String = ""

        txt_Insert = Me.armo_insert()
        txt_Insert += Me.asigno_valores_insert(valores)

        'MsgBox(txt_Insert)

        Dim estado As String = Me._ejecutar(txt_Insert)
        If Me.configurar_conexion = tipo_conexion.transaccion Then
            If estado = "ok" Then
                Return "ok"
            Else
                Return "error"
            End If
        Else
            Me.conexion.Close()
            Return ("ok")
        End If

    End Function
    Private Function armo_insert() As String
        Dim tabla As Data.DataTable
        Dim contador As Integer = 0
        Dim txt As String = ""

        'tabla = Me.leo_tabla()
        tabla = Me.leo_estructura()

        txt = "insert into " & Me.tabla & " ("

        While tabla.Columns.Count() > contador
            txt += tabla.Columns(contador).Caption
            contador += 1
            If contador < tabla.Columns.Count() Then
                txt += ", "
            End If
        End While
        txt += ") values ("
        Return txt
    End Function
    Private Function asigno_valores_insert(ByVal txt_insertar As String) As String
        Dim objeto_tabla As Data.DataTable     'tabla que contiene la estructura en la base
        Dim contador As Integer = 0             'contador de bucle
        Dim campo As Integer = 0                'ubicación del campo 
        Dim coma As Integer = 0                 'ubicacion de la "," 
        Dim igual As Integer = 0                'ubicación del "="
        Dim txt_origen As String = ""           'texto origen 
        Dim nombre_campo As String = ""         'campo a buscar 
        Dim valor As String = ""                'valor del campo 
        Dim txt As String = ""                  'texto que se va formando para el insert
        Dim tipo_dato As String = ""

        'objeto_tabla = Me.leo_tabla()
        objeto_tabla = Me.leo_estructura()

        txt_origen = txt_insertar.ToUpper.Trim()

        While objeto_tabla.Columns.Count() > contador

            nombre_campo = objeto_tabla.Columns(contador).Caption.ToUpper.Trim()
            tipo_dato = objeto_tabla.Columns(contador).DataType.ToString

            'MsgBox(nombre_campo & ": " & tipo_dato)

            campo = txt_origen.IndexOf(nombre_campo)
            If campo <> -1 Then
                igual = txt_origen.IndexOf("=", campo)
                coma = txt_origen.IndexOf(",", campo)
            Else
                igual = -1
                coma = -1
            End If

            If campo <> -1 Then
                igual = txt_origen.IndexOf("=", campo)
                coma = txt_origen.IndexOf(",", campo)
                If coma <> -1 Then
                    valor = Me.acomodo_al_tipo_dato(txt_origen.Substring(igual + 1, coma - igual - 1), tipo_dato)
                Else
                    valor = Me.acomodo_al_tipo_dato(txt_origen.Substring(igual + 1), tipo_dato)
                End If
            Else
                valor = "null"
            End If

            txt += valor

            contador += 1
            If contador < objeto_tabla.Columns.Count() Then
                txt += ", "
            End If

        End While

        txt += ")"
        Return txt

    End Function
    Private Function acomodo_al_tipo_dato(ByVal texto As String, ByVal tipo_dato As String) As String
        Select Case tipo_dato
            Case "System.String"
                Return "'" & texto & "'"
            Case "System.Int16"
                Return texto
            Case "System.Int32"
                Return texto
            Case "System.Int64"
                Return texto
            Case "System.Byte"
                Return texto
            Case "System.Double"
                Return texto
            Case "System.Decimal"
                Return texto
            Case "System.DateTime"
                If motor = motores.OleDb Then
                    Return "#" & texto & "#"
                Else
                    Return "'" & texto & "'"
                End If
            Case "System.DateTimeKind"
                If motor = motores.OleDb Then
                    Return "#" & texto & "#"
                Else
                    Return "'" & texto & "'"
                End If
            Case Else
                Return texto
        End Select
    End Function
    Private Function leo_estructura() As Data.DataTable
        Dim sql As String
        Dim tabla As New DataTable

        Select Case motor
            Case motores.OleDb
                sql = "select top 1 * from " & Me.tabla
                tabla = Me._consulta(sql)
                Return tabla
            Case motores.sqlserver
                sql = "select top 1 * from " & Me.tabla
                tabla = Me._consulta(sql)
                Return tabla
        End Select
        Return tabla
    End Function

End Class