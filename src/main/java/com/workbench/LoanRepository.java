package com.workbench;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by RdDvls on 1/10/17.
 */
public interface LoanRepository extends CrudRepository<Loan, Integer>{
    Loan findByLoanID(int loanID);

}
