package com.bsn.BSN.Problems.Store.Server.entity;

import java.io.Serializable;
import lombok.*;
import jakarta.persistence.*;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class ProblemTagKey implements Serializable {
    private Long problemId;
    private Long tagId;
}

