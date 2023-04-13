package org.maxtorm.ledger.obj;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "account")
@Table(name = "account")
public class Account extends AbstractTimestampEntity {
    @Id
    @Column(name = "account_id", columnDefinition = "VARCHAR", nullable = false)
    private String accountId = UUID.randomUUID().toString();

    @Column(name = "root_account_id", columnDefinition = "VARCHAR", nullable = false)
    private String rootAccountId = accountId;

    @Column(name = "parent_account_id", columnDefinition = "VARCHAR", nullable = false)
    private String parentAccountId = accountId;

    @Column(name = "depth", columnDefinition = "BIGINT", nullable = false)
    private Integer depth = 0;

    @Column(name = "name", columnDefinition = "VARCHAR", nullable = false)
    private String name = "";

    @Column(name = "icon_url", columnDefinition = "VARCHAR", nullable = false)
    private String iconUrl = "https://baidu.com";

    @Column(name = "balance", columnDefinition = "INTEGER", nullable = false)
    private Long balance = 0L;

    @Column(name = "total_inflow", columnDefinition = "INTEGER", nullable = false)
    private Long totalInflow = 0L;

    @Column(name = "total_outflow", columnDefinition = "INTEGER", nullable = false)
    private Long totalOutflow = 0L;

    public URI getIconUrl() {
        return URI.create(iconUrl);
    }

    public void setIconUrl(URI iconUrl) {
        this.iconUrl = iconUrl.toString();
    }

}
