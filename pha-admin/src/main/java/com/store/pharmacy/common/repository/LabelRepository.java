package com.store.pharmacy.common.repository;

import com.store.pharmacy.common.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, String> {

    Label findByLabelCodeAndLangAndEnabledTrue(String labelCode, String lang);

    Label findByLabelCodeAndLang(String labelcode, String lang);
}
