package model.DAOSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.DataObject.Usuario;
import model.IDAO.DAOException;
import model.IDAO.IUsuarioDAO;

public class UsuarioDAO implements IUsuarioDAO{
	private final static String GETUSERBYNAMEPASS = "SELECT id,name, email, password ,phone FROM usuario WHERE email= ? AND password = ?";
	private final static String LOGINMENU = "SELECT name,password FROM User WHERE name LIKE ? AND password LIKE ?";
	private final static String GETUSERBYID = "SELECT id,name, email, password ,phone FROM User WHERE id = ?";
	private final static String GETUSERBYNAME = "SELECT id,name, email, password ,phone FROM User WHERE name = ?";
	private final static String GETUSERBYEMAIL = "SELECT id,name, email, password ,phone FROM User WHERE email = ?";
	private final static String GETUSERS = "SELECT id,name, email, password ,phone FROM User";
	private final static String DELETEUSER = "DELETE FROM User WHERE id =? ";
	private final static String CREATEUSER = "INSERT INTO User (name, email, password,phone ) VALUES (?,?,?,?) ";
	private final static String EDITUSER = "UPDATE User SET name= ?, email = ?, password=? WHERE id=?";
	private final static String GETUSERBOOKS = "Select id,tittle,year,description FROM Book WHERE user_id = ?";
	private Connection con;

	@Override
	public void crear(Usuario aux) {
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(CREATEUSER, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, aux.getName());
				ps.setString(2, aux.getEmail());
				ps.setString(3, aux.getPassword());
				ps.setString(4, aux.getPhone());
				ps.executeUpdate();
				// Only execute if u insert RETURN_GENERATED_KEYS
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					aux.setId(rs.getLong(1));
				}
			} catch (SQLException err) {
				throw new DAOException("Error SQL en : ", err);
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
			}
		}

	}

	@Override
	public void editar(Usuario aux) {
		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(EDITUSER);

				ps.setString(1, aux.getName());
				ps.setString(2, aux.getEmail());
				ps.setString(3, aux.getPassword());
				ps.setLong(4, aux.getId());
				ps.executeUpdate();

			} catch (SQLException err) {
				throw new DAOException("Error SQL en : ", err);
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
			}
		}
	}

	@Override
	public void borrar(Long id) {

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(DELETEUSER);
				ps.setLong(1, id);
				ps.executeUpdate();
			} catch (SQLException err) {
				throw new DAOException("Error SQL :", err);
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
			}
		}
	}

	@Override
	public List<Usuario> mostrarTodos() {
		List<Usuario> result = new ArrayList<Usuario>();

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETUSERS);
				rs = ps.executeQuery();

				while (rs.next()) {

					result.add(convertir(rs));
				}

			} catch (SQLException err) {
				throw new DAOException("Error SQL :", err);

			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
			}
		}
		return result;
	}

	@Override
	public Usuario mostrarPorId(Long id) {
		Usuario result = new Usuario();

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETUSERBYID);
				ps.setLong(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					result = convertir(rs);
				} else {
					throw new DAOException("No se ha encontrado ese registro");
				}
			} catch (SQLException err) {
				throw new DAOException("Error SQL : ", err);
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
			}
		}
		return result;
	}

	@Override
	public Usuario getUserByName(String name) throws DAOException {
		Usuario result = new Usuario();

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETUSERBYNAME);
				ps.setString(1, name);
				rs = ps.executeQuery();
				if (rs.next()) {
					result = convertir(rs);
				} else {
					throw new DAOException("No se ha encontrado ese registro");
				}
			} catch (SQLException err) {
				throw new DAOException("Error SQL : ", err);
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
			}
		}
		return result;
	}
	

	@Override
	public Usuario getUserByEmail(String email) throws DAOException {
		Usuario result = new Usuario();

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETUSERBYEMAIL);
				ps.setString(1, email);
				rs = ps.executeQuery();
				if (rs.next()) {
					result = convertir(rs);
				} else {
					throw new DAOException("No se ha encontrado ese registro");
				}
			} catch (SQLException err) {
				throw new DAOException("Error SQL : ", err);
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
			}
		}
		return result;
	}

	@Override
	public boolean logIn(String nombre, String contraseña) throws DAOException {
		boolean logResult = false;
		try {
			//Conexion
			Connection con = MariaDBConexion.getConexion();
			PreparedStatement ps = con.prepareStatement(LOGINMENU);
			ps.setString(1, nombre);
			ps.setString(2, contraseña);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				logResult = false;
			} else {
				logResult = true;
			}
		} catch (SQLException err) {
			throw new DAOException("Error SQL :", err);
		}
		return logResult;
	}

	@Override
	public Usuario getUsuarioByNombreContraseña(String eAux, String cAux) throws DAOException {

		Usuario result = new Usuario();

		con = MariaDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETUSERBYNAMEPASS);
				ps.setString(1, eAux);
				ps.setString(2, cAux);
				rs = ps.executeQuery();
				if (rs.next()) {
					result = convertir(rs);
				} else {
					throw new DAOException("No se ha encontrado ese registro");
				}
			} catch (SQLException err) {
				throw new DAOException("Error SQL : ", err);
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException err) {
						throw new DAOException("Error SQL :", err);
					}
				}
			}
		}
		return result;
	}
	public Usuario convertir(ResultSet rs) throws SQLException {
		Usuario user = new Usuario();
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setPhone(rs.getString("phone"));
		return user;
	}
	
}
