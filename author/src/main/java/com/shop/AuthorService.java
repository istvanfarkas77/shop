package com.shop;

import org.apache.commons.collections.IteratorUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
class AuthorService {

    DataSource dataSource;

    JdbcTemplate jdbcTemplate;

    AuditClient auditClient;

    AuthorRepository authorRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public AuthorService(DataSource dataSource, AuditClient auditClient, AuthorRepository authorRepository) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.auditClient = auditClient;
        this.authorRepository = authorRepository;
    }

    public AuthorDTO findByName(String name) {
        Author author = authorRepository.findByName(name);

        return author != null ? modelMapper.map(author, AuthorDTO.class) : null;
    }

    public AuthorDTO save(String authorName) {
        int id = jdbcTemplate.update("insert into T_AUTHOR (NAME) values (?)", authorName);

        Author author = (Author)jdbcTemplate.queryForObject(
                "select * from T_AUTHOR  where ID = ?",
                new Object[]{id},
                new RowMapper<Author>() {
                    @Override
                    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Author(resultSet.getLong("ID"), resultSet.getString("NAME"));
                    }
                });

        auditClient.save(String.format("Author save with name '%s'.", authorName));

        return modelMapper.map(author, AuthorDTO.class);
    }

    public AuthorDTO findById(Long id) {
        return modelMapper.map(authorRepository.findOne(id), AuthorDTO.class);
    }

    public List<AuthorDTO> get() {
        List<Author> authors = IteratorUtils.toList(authorRepository.findAll().iterator());

        return authors.stream().map(pojo -> modelMapper.map(pojo, AuthorDTO.class)).collect(Collectors.toList());
    }
}
