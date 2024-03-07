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
import { EscrowTransactionDetailsService } from '../service/escrow-transaction-details.service';
import { IEscrowTransactionDetails } from '../escrow-transaction-details.model';
import { EscrowTransactionDetailsFormService, EscrowTransactionDetailsFormGroup } from './escrow-transaction-details-form.service';

@Component({
  standalone: true,
  selector: 'jhi-escrow-transaction-details-update',
  templateUrl: './escrow-transaction-details-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class EscrowTransactionDetailsUpdateComponent implements OnInit {
  isSaving = false;
  escrowTransactionDetails: IEscrowTransactionDetails | null = null;

  disbursementsSharedCollection: IDisbursement[] = [];
  repaymentsSharedCollection: IRepayment[] = [];

  editForm: EscrowTransactionDetailsFormGroup = this.escrowTransactionDetailsFormService.createEscrowTransactionDetailsFormGroup();

  constructor(
    protected escrowTransactionDetailsService: EscrowTransactionDetailsService,
    protected escrowTransactionDetailsFormService: EscrowTransactionDetailsFormService,
    protected disbursementService: DisbursementService,
    protected repaymentService: RepaymentService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareDisbursement = (o1: IDisbursement | null, o2: IDisbursement | null): boolean =>
    this.disbursementService.compareDisbursement(o1, o2);

  compareRepayment = (o1: IRepayment | null, o2: IRepayment | null): boolean => this.repaymentService.compareRepayment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ escrowTransactionDetails }) => {
      this.escrowTransactionDetails = escrowTransactionDetails;
      if (escrowTransactionDetails) {
        this.updateForm(escrowTransactionDetails);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const escrowTransactionDetails = this.escrowTransactionDetailsFormService.getEscrowTransactionDetails(this.editForm);
    if (escrowTransactionDetails.id !== null) {
      this.subscribeToSaveResponse(this.escrowTransactionDetailsService.update(escrowTransactionDetails));
    } else {
      this.subscribeToSaveResponse(this.escrowTransactionDetailsService.create(escrowTransactionDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEscrowTransactionDetails>>): void {
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

  protected updateForm(escrowTransactionDetails: IEscrowTransactionDetails): void {
    this.escrowTransactionDetails = escrowTransactionDetails;
    this.escrowTransactionDetailsFormService.resetForm(this.editForm, escrowTransactionDetails);

    this.disbursementsSharedCollection = this.disbursementService.addDisbursementToCollectionIfMissing<IDisbursement>(
      this.disbursementsSharedCollection,
      escrowTransactionDetails.disbursement,
    );
    this.repaymentsSharedCollection = this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(
      this.repaymentsSharedCollection,
      escrowTransactionDetails.repayment,
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
            this.escrowTransactionDetails?.disbursement,
          ),
        ),
      )
      .subscribe((disbursements: IDisbursement[]) => (this.disbursementsSharedCollection = disbursements));

    this.repaymentService
      .query()
      .pipe(map((res: HttpResponse<IRepayment[]>) => res.body ?? []))
      .pipe(
        map((repayments: IRepayment[]) =>
          this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(repayments, this.escrowTransactionDetails?.repayment),
        ),
      )
      .subscribe((repayments: IRepayment[]) => (this.repaymentsSharedCollection = repayments));
  }
}
