package com.dlink.dto;

import com.dlink.assertion.Asserts;
import com.dlink.job.JobConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * StudioExecuteDTO
 *
 * @author wenmo
 * @since 2021/5/30 11:09
 */
@Getter
@Setter
public class StudioExecuteDTO extends AbstractStatementDTO{
    // RUN_MODE
    private String type;
    private String dialect;
    private boolean useResult;
    private boolean statementSet;
    private boolean useSession;
    private String session;
    private boolean useRemote;
    private Integer clusterId;
    private Integer clusterConfigurationId;
    private Integer databaseId;
    private Integer jarId;
    private boolean fragment;
//    private String statement;
    private String jobName;
    private Integer taskId;
    private Integer maxRowNum;
    private Integer checkPoint;
    private Integer parallelism;
    private Integer savePointStrategy;
    private String savePointPath;
    private String configJson;
    private static final ObjectMapper mapper = new ObjectMapper();

    public JobConfig getJobConfig() {
        Map<String,String> config = new HashMap<>();
        JsonNode paras = null;
        if(Asserts.isNotNullString(configJson)) {
            try {
                paras = mapper.readTree(configJson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            paras.forEach((JsonNode node) -> {
                config.put(node.get("key").asText(), node.get("value").asText());
                }
            );
        }
        return new JobConfig(
                type,useResult, useSession, session, useRemote, clusterId,
                clusterConfigurationId,jarId, taskId, jobName, fragment,statementSet,
                maxRowNum, checkPoint, parallelism,savePointStrategy, savePointPath,config);
    }
}
