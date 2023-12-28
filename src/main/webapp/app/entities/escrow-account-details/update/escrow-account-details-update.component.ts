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
import { EscrowAccountDetailsService } from '../service/escrow-account-details.service';
import { IEscrowAccountDetails } from '../escrow-account-details.model';
import { EscrowAccountDetailsFormService, EscrowAccountDetailsFormGroup } from './escrow-account-details-form.service';

@Component({
  standalone: true,
  selector: 'jhi-escrow-account-details-update',
  templateUrl: './escrow-account-details-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class EscrowAccountDetailsUpdateComponent implements OnInit {
  isSaving = false;
  escrowAccountDetails: IEscrowAccountDetails | null = null;

  disbursementsSharedCollection: IDisbursement[] = [];
  repaymentsSharedCollection: IRepayment[] = [];

  editForm: EscrowAccountDetailsFormGroup = this.escrowAccountDetailsFormService.createEscrowAccountDetailsFormGroup();

  constructor(
    protected escrowAccountDetailsService: EscrowAccountDetailsService,
    protected escrowAccountDetailsFormService: EscrowAccountDetailsFormService,
    protected disbursementService: DisbursementService,
    protected repaymentService: RepaymentService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareDisbursement = (o1: IDisbursement | null, o2: IDisbursement | null): boolean =>
    this.disbursementService.compareDisbursement(o1, o2);

  compareRepayment = (o1: IRepayment | null, o2: IRepayment | null): boolean => this.repaymentService.compareRepayment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ escrowAccountDetails }) => {
      this.escrowAccountDetails = escrowAccountDetails;
      if (escrowAccountDetails) {
        this.updateForm(escrowAccountDetails);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const escrowAccountDetails = this.escrowAccountDetailsFormService.getEscrowAccountDetails(this.editForm);
    if (escrowAccountDetails.id !== null) {
      this.subscribeToSaveResponse(this.escrowAccountDetailsService.update(escrowAccountDetails));
    } else {
      this.subscribeToSaveResponse(this.escrowAccountDetailsService.create(escrowAccountDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEscrowAccountDetails>>): void {
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

  protected updateForm(escrowAccountDetails: IEscrowAccountDetails): void {
    this.escrowAccountDetails = escrowAccountDetails;
    this.escrowAccountDetailsFormService.resetForm(this.editForm, escrowAccountDetails);

    this.disbursementsSharedCollection = this.disbursementService.addDisbursementToCollectionIfMissing<IDisbursement>(
      this.disbursementsSharedCollection,
      escrowAccountDetails.disbursement,
    );
    this.repaymentsSharedCollection = this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(
      this.repaymentsSharedCollection,
      escrowAccountDetails.repayment,
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
            this.escrowAccountDetails?.disbursement,
          ),
        ),
      )
      .subscribe((disbursements: IDisbursement[]) => (this.disbursementsSharedCollection = disbursements));

    this.repaymentService
      .query()
      .pipe(map((res: HttpResponse<IRepayment[]>) => res.body ?? []))
      .pipe(
        map((repayments: IRepayment[]) =>
          this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(repayments, this.escrowAccountDetails?.repayment),
        ),
      )
      .subscribe((repayments: IRepayment[]) => (this.repaymentsSharedCollection = repayments));
  }
}
