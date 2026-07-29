package com.redwood.scheduler.custom.snowflake.importer;

import com.redwood.scheduler.api.model.JobFile;
import com.redwood.scheduler.custom.snowflake.SnowflakeResponse;
import com.redwood.scheduler.custom.snowflake.model.Kpi4;
import com.redwood.scheduler.custom.snowflake.repository.Kpi4Repository;
import com.redwood.scheduler.custom.snowflake.util.CsvUtils;

import java.util.ArrayList;
import java.util.List;

import static com.redwood.scheduler.custom.snowflake.schema.Kpi4Schema.*;
import static com.redwood.scheduler.custom.snowflake.util.SnowflakeValueSanitizer.safe;

public class Kpi4CsvImporter
        extends BaseCsvImporter {

    private final Kpi4Repository repository;

    public Kpi4CsvImporter(
            Kpi4Repository repository) {

        this.repository = repository;
    }

    public SnowflakeResponse importFile(
            JobFile file)
            throws Exception {

        List<Kpi4> records =
                new ArrayList<>();


        for (String[] row : CsvUtils.read(file)) {

            Kpi4 record = new Kpi4();

            record.setJobId(safe(get(row, 0), JOB_ID));
            record.setJobStatus(safe(get(row, 1), JOB_STATUS));
            record.setStartTime(safe(get(row, 2), START_TIME));
            record.setEndTime(safe(get(row, 3), END_TIME));
            record.setCompanyCode(safe(get(row, 4), COMPANY_CODE));
            record.setAccount(safe(get(row, 5), ACCOUNT));
            record.setAccountGroup(safe(get(row, 6), ACCOUNT_GROUP));
            record.setListOfPreparer(safe(get(row, 7), LIST_OF_PREPARER));
            record.setPreparerResponder(safe(get(row, 8), PREPARER_RESPONDER));
            record.setListOfApprover(safe(get(row, 9), LIST_OF_APPROVER));

            record.setApproverResponder(safe(get(row, 10), APPROVER_RESPONDER));
            record.setListOfReviewer(safe(get(row, 11), LIST_OF_REVIEWER));
            record.setReviewerResponder(safe(get(row, 12), REVIEWER_RESPONDER));
            record.setCertificationStatus(safe(get(row, 13), CERTIFICATION_STATUS));
            record.setPreparerTimestamp(safe(get(row, 14), PREPARER_TIMESTAMP));
            record.setApproverTimestamp(safe(get(row, 15), APPROVER_TIMESTAMP));
            record.setReviewerTimestamp(safe(get(row, 16), REVIEWER_TIMESTAMP));
            record.setSapBalance(safe(get(row, 17), SAP_BALANCE));
            record.setCurrency(safe(get(row, 18), CURRENCY));
            record.setPeriod(safe(get(row, 19), PERIOD));

            record.setFiscalYear(safe(get(row, 20), FISCAL_YEAR));
            record.setPreparerComment(safe(get(row, 21), PREPARER_COMMENT));
            record.setApproverComment(safe(get(row, 22), APPROVER_COMMENT));
            record.setReviewerComment(safe(get(row, 23), REVIEWER_COMMENT));
            record.setCertificationId(safe(get(row, 24), CERTIFICATIONID));
            record.setReconciliationType(safe(get(row, 25), RECONCILIATION_TYPE));
            record.setAutoCertificationRule(safe(get(row, 26), AUTO_CERTIFICATION_RULE));
            record.setAutoCertified(safe(get(row, 27), AUTO_CERTIFIED));
            record.setDueDate(safe(get(row, 28), DUE_DATE));
            record.setAccountCategory(safe(get(row, 29), ACCOUNT_CATEGORY));

            record.setAnalyzedQuantity(safe(get(row, 30), ANALYZED_QUANTITY));
            record.setAnalyzedBalance(safe(get(row, 31), ANALYZED_BALANCE));
            record.setUnanalyzedQuantity(safe(get(row, 32), UNANALYZED_QUANTITY));
            record.setUnanalyzedBalance(safe(get(row, 33), UNANALYZED_BALANCE));
            record.setRequestId(safe(get(row, 34), REQUESTID));
            record.setFilename(safe(file.getName(), FILENAME));

            records.add(record);
        }


        return repository.save(records);
    }
}