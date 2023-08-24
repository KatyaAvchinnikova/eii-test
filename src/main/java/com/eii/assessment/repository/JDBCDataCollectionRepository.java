package com.eii.assessment.repository;

import com.eii.assessment.dto.DataCollectionDto;
import com.eii.assessment.dto.DataCollectionCreateDto;
import com.eii.assessment.exception.ResourceNotFoundException;
import com.eii.assessment.mapper.DataCollectionRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JDBCDataCollectionRepository implements DataCollectionRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DataCollectionRowMapper dataCollectionRowMapper;

    @Override
    public int save(DataCollectionCreateDto dataCollectionCreateDto) {
        String sql = "INSERT INTO eii_test.data_collections (file_id_orders, file_id_assets, file_id_inventory, status, tag, note) " +
                "VALUES (:file_id_orders, :file_id_asserts, :file_id_inventory, :status, :tag, :note)";

        MapSqlParameterSource parameters = new MapSqlParameterSource("file_id_orders", dataCollectionCreateDto.getFileIdOrders());
        parameters.addValue("file_id_asserts",      dataCollectionCreateDto.getFileIdAssets());
        parameters.addValue("file_id_inventory",    dataCollectionCreateDto.getFileIdInventory());
        parameters.addValue("status",               dataCollectionCreateDto.getStatus());
        parameters.addValue("tag",                  dataCollectionCreateDto.getTag());
        parameters.addValue("note",                 dataCollectionCreateDto.getTag());

        return jdbcTemplate.update(sql, parameters);
    }

    @Override
    public int update(DataCollectionDto dataCollectionDto) {
        String sql = "UPDATE eii_test.data_collections SET file_id_orders = :file_id_orders, " +
                "file_id_assets = file_id_assets, " +
                "file_id_inventory=:file_id_inventory, " +
                "status = :status, " +
                "tag = :tag, " +
                "note = :note, " +
                "updated_on = :updated_on " +
                "WHERE id=:id";

        MapSqlParameterSource parameters = new MapSqlParameterSource("file_id_orders", dataCollectionDto.getFileIdOrders());
        parameters.addValue("file_id_asserts",      dataCollectionDto.getFileIdAssets());
        parameters.addValue("file_id_inventory",    dataCollectionDto.getFileIdInventory());
        parameters.addValue("status",               dataCollectionDto.getStatus());
        parameters.addValue("tag",                  dataCollectionDto.getTag());
        parameters.addValue("note",                 dataCollectionDto.getNote());
        parameters.addValue("id",                   dataCollectionDto.getId());
        parameters.addValue("updated_on",           dataCollectionDto.getUpdatedOn());
        return jdbcTemplate.update(sql, parameters);
    }

    @Override
    public DataCollectionDto findById(Integer id) {
        String sql = "SELECT id, " +
                "created_on, " +
                "updated_on, " +
                "file_id_orders, " +
                "file_id_assets, " +
                "file_id_inventory, " +
                "status, " +
                "tag, " +
                "note " +
                "FROM eii_test.data_collections " +
                "WHERE id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource("id", id);

        try {
            DataCollectionDto dataCollectionDto = jdbcTemplate.queryForObject(sql, parameters, dataCollectionRowMapper);

            return dataCollectionDto;
        } catch (RuntimeException ex) {

            throw new ResourceNotFoundException("data collection", "id", id);
        }
    }

    @Override
    public List<DataCollectionDto> findAll() {
        String sql = "SELECT id, " +
                "created_on, " +
                "updated_on, " +
                "file_id_orders, " +
                "file_id_assets, " +
                "file_id_inventory, " +
                "status, " +
                "tag, " +
                "note " +
                "FROM eii_test.data_collections";

        return new ArrayList<>(jdbcTemplate.query(sql, new MapSqlParameterSource(), dataCollectionRowMapper));
    }

    @Override
    public int deleteAll() {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }
}
