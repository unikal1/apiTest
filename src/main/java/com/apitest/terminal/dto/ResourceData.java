package com.apitest.terminal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * <br>package name   : com.apitest.terminal.dto
 * <br>file name      : ResourceData
 * <br>date           : 2024-08-27
 * <pre>
 * <span style="color: white;">[description]</span>
 *
 * </pre>
 * <pre>
 * <span style="color: white;">usage:</span>
 * {@code
 *
 * } </pre>
 * <pre>
 * modified log :
 * ====================================================
 * DATE           AUTHOR               NOTE
 * ----------------------------------------------------
 * 2024-08-27        jack8              init create
 * </pre>
 */
@Data
public class ResourceData {


    private List<CPUUsage> cpu_usage;
    private List<CPUTemperature> cpu_temp;
    private MemoryUsage memory_usage;
    private DiskUsage disk_usage;
    private List<ProcessInfo> process_list;

    @Data
    public static class CPUUsage {
        private String core;
        private String usage;
    }

    @Data
    public static class CPUTemperature {
        private String core;
        private String temperature;
    }

    @Data
    public static class MemoryUsage {
        private String total_memory;
        private String used_memory;
        private String free_memory;
        private String swap_total;
        private String swap_used;
        private String swap_free;
    }

    @Data
    public static class DiskUsage {
        private String total_disk;
        private String used_disk;
        private String available_disk;
    }

    @Data
    public static class ProcessInfo {
        private String user;
        private String pid;
        private String cpu_usage;
        private String mem_usage;
        private String command;
    }
}
