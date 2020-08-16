package com.mnaufalazwar.springbootjbdc.dao;

import com.mnaufalazwar.springbootjbdc.entity.Mahasiswa;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MahasiswaRepository implements MahasiswaDao{

    /**

    private final String INSERT = "INSERT INTO mahasiswa (nim, nama, ipk, jurusan) "
            + "	VALUES (?,?,?,?)";
    private final String UPDATE = "UPDATE mahasiswa SET nama=?, ipk=?, jurusan=? WHERE nim=?";
    private final String DELETE = "DELETE FROM mahasiswa WHERE nim=?";
    private final String SELECT_ALL = "SELECT nim,nama,ipk,jurusan FROM mahasiswa";
    private final String SELECT_BY_NIM = "SELECT nim,nama,ipk,jurusan FROM mahasiswa WHERE nim=?";

    private static Logger LOGGER = LoggerFactory.getLogger(MahasiswaRepository.class.getName());

    @Autowired
    private DataSource dataSource;

    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public boolean insert(Mahasiswa mahasiswa) {

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, mahasiswa.getNim());
            preparedStatement.setString(2, mahasiswa.getNama());
            preparedStatement.setFloat(3, mahasiswa.getIpk());
            preparedStatement.setString(4, mahasiswa.getJurusan());
            return preparedStatement.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            LOGGER.error(null, e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }

        }
        return false;

    }

    @Override
    public boolean update(Mahasiswa mahasiswa) {
        return false;
    }

    @Override
    public boolean delete(Mahasiswa mahasiswa) {
        return false;
    }

    @Override
    public Mahasiswa getByNim(String nim) {
        return null;
    }

    @Override
    public List<Mahasiswa> getAll() {
        return null;
    }

    */

    private final String INSERT = "INSERT INTO mahasiswa (nim, nama, ipk, jurusan) "
            + "	VALUES (?,?,?,?)";
    private final String UPDATE = "UPDATE mahasiswa SET nama=?, ipk=?, jurusan=? WHERE nim=?";
    private final String DELETE = "DELETE FROM mahasiswa WHERE nim=?";
    private final String SELECT_ALL = "SELECT nim,nama,ipk,jurusan FROM mahasiswa";
    private final String SELECT_BY_NIM = "SELECT nim,nama,ipk,jurusan FROM mahasiswa WHERE nim=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insert(Mahasiswa mahasiswa) {
        return jdbcTemplate.update(INSERT, new Object[]{mahasiswa.getNim(), mahasiswa.getNama(), mahasiswa.getIpk(), mahasiswa.getJurusan()}) > 0 ? true : false;
    }

    @Override
    public boolean update(Mahasiswa mahasiswa) {
        return jdbcTemplate.update(UPDATE, new Object[]{mahasiswa.getNama(), mahasiswa.getIpk(), mahasiswa.getJurusan(), mahasiswa.getNim()}) > 0 ? true : false;
    }

    @Override
    public boolean delete(Mahasiswa mahasiswa) {
        return jdbcTemplate.update(DELETE, new Object[]{mahasiswa.getNim()}) > 0 ? true : false;
    }

    @Override
    public Mahasiswa getByNim(String nim) {
        return jdbcTemplate.queryForObject(SELECT_BY_NIM, new Object[]{nim}, new MahasiswaRowMapper());
    }

    @Override
    public List<Mahasiswa> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new MahasiswaRowMapper());
    }

    private class MahasiswaRowMapper implements RowMapper<Mahasiswa> {

        @Override
        public Mahasiswa mapRow(ResultSet resultSet, int i) throws SQLException {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNim(resultSet.getString("nim"));
            mahasiswa.setNama(resultSet.getString("nama"));
            mahasiswa.setJurusan(resultSet.getString("jurusan"));
            mahasiswa.setIpk(resultSet.getFloat("ipk"));
            return mahasiswa;
        }
    }
}
