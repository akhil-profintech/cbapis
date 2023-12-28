import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { DisbursementService } from 'app/entities/disbursement/service/disbursement.service';
import { IRepayment } from 'app/entities/repayment/repayment.model';
import { RepaymentService } from 'app/entities/repayment/service/repayment.service';
import { CreditAccountDetailsService } from '../service/credit-account-details.service';
import { ICreditAccountDetails } from '../credit-account-details.model';
import { CreditAccountDetailsFormService, CreditAccountDetailsFormGroup } from './credit-account-details-form.service';

@Component({
  standalone: true,
  selector: 'jhi-credit-account-details-update',
  templateUrl: './credit-account-details-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CreditAccountDetailsUpdateComponent implements OnInit {
  isSaving = false;
  creditAccountDetails: ICreditAccountDetails | null = null;

  disbursementsSharedCollection: IDisbursement[] = [];
  repaymentsSharedCollection: IRepayment[] = [];

  editForm: CreditAccountDetailsFormGroup = this.creditAccountDetailsFormService.createCreditAccountDetailsFormGroup();

  constructor(
    protected creditAccountDetailsService: CreditAccountDetailsService,
    protected creditAccountDetailsFormService: CreditAccountDetailsFormService,
    protected disbursementService: DisbursementService,
    protected repaymentService: RepaymentService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareDisbursement = (o1: IDisbursement | null, o2: IDisbursement | null): boolean =>
    this.disbursementService.compareDisbursement(o1, o2);

  compareRepayment = (o1: IRepayment | null, o2: IRepayment | null): boolean => this.repaymentService.compareRepayment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ creditAccountDetails }) => {
      this.creditAccountDetails = creditAccountDetails;
      if (creditAccountDetails) {
        this.updateForm(creditAccountDetails);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const creditAccountDetails = this.creditAccountDetailsFormService.getCreditAccountDetails(this.editForm);
    if (creditAccountDetails.id !== null) {
      this.subscribeToSaveResponse(this.creditAccountDetailsService.update(creditAccountDetails));
    } else {
      this.subscribeToSaveResponse(this.creditAccountDetailsService.create(creditAccountDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICreditAccountDetails>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(creditAccountDetails: ICreditAccountDetails): void {
    this.creditAccountDetails = creditAccountDetails;
    this.creditAccountDetailsFormService.resetForm(this.editForm, creditAccountDetails);

    this.disbursementsSharedCollection = this.disbursementService.addDisbursementToCollectionIfMissing<IDisbursement>(
      this.disbursementsSharedCollection,
      creditAccountDetails.disbursement,
    );
    this.repaymentsSharedCollection = this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(
      this.repaymentsSharedCollection,
      creditAccountDetails.repayment,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.disbursementService
      .query()
      .pipe(map((res: HttpResponse<IDisbursement[]>) => res.body ?? []))
      .pipe(
        map((disbursements: IDisbursement[]) =>
          this.disbursementService.addDisbursementToCollectionIfMissing<IDisbursement>(
            disbursements,
            this.creditAccountDetails?.disbursement,
          ),
        ),
      )
      .subscribe((disbursements: IDisbursement[]) => (this.disbursementsSharedCollection = disbursements));

    this.repaymentService
      .query()
      .pipe(map((res: HttpResponse<IRepayment[]>) => res.body ?? []))
      .pipe(
        map((repayments: IRepayment[]) =>
          this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(repayments, this.creditAccountDetails?.repayment),
        ),
      )
      .subscribe((repayments: IRepayment[]) => (this.repaymentsSharedCollection = repayments));
  }
}
