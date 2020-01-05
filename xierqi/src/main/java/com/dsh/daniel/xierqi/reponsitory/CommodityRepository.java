package com.dsh.daniel.xierqi.reponsitory;

import com.dsh.daniel.xierqi.domain.Commodity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends ElasticsearchRepository<Commodity, String> {
}
