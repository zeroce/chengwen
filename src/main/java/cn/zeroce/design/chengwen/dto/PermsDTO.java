package cn.zeroce.design.chengwen.dto;

import java.util.List;

/**
 * @author: zeroce
 * @date 20.5.4 16:57
 */
public class PermsDTO {
    private Integer id;
    private String label;
    private List<PermsDTO> children;

    public PermsDTO() { }

    public PermsDTO(Integer id, String label, List<PermsDTO> children) {
        this.id = id;
        this.label = label;
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<PermsDTO> getChildren() {
        return children;
    }

    public void setChildren(List<PermsDTO> children) {
        this.children = children;
    }
}
