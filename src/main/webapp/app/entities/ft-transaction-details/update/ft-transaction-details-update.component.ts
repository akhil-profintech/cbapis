import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IParticipantSettlement } from 'app/entities/participant-settlement/participant-settlement.model';
import { ParticipantSettlementService } from 'app/entities/participant-settlement/service/participant-settlement.service';
import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { DisbursementService } from 'app/entities/disbursement/service/disbursement.service';
import { IRepayment } from 'app/entities/repayment/repayment.model';
import { RepaymentService } from 'app/entities/repayment/service/repayment.service';
import { FTTransactionDetailsService } from '../service/ft-transaction-details.service';
import { IFTTransactionDetails } from '../ft-transaction-details.model';
import { FTTransactionDetailsFormService, FTTransactionDetailsFormGroup } from './ft-transaction-details-form.service';

@Component({
  standalone: true,
  selector: 'jhi-ft-transaction-details-update',
  templateUrl: './ft-transaction-details-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FTTransactionDetailsUpdateComponent implements OnInit {
  isSaving = false;
  fTTransactionDetails: IFTTransactionDetails | null = null;

  participantSettlementsSharedCollection: IParticipantSettlement[] = [];
  disbursementsSharedCollection: IDisbursement[] = [];
  repaymentsSharedCollection: IRepayment[] = [];

  editForm: FTTransactionDetailsFormGroup = this.fTTransactionDetailsFormService.createFTTransactionDetailsFormGroup();

  constructor(
    protected fTTransactionDetailsService: FTTransactionDetailsService,
    protected fTTransactionDetailsFormService: FTTransactionDetailsFormService,
    protected participantSettlementService: ParticipantSettlementService,
    protected disbursementService: DisbursementService,
    protected repaymentService: RepaymentService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareParticipantSettlement = (o1: IParticipantSettlement | null, o2: IParticipantSettlement | null): boolean =>
    this.participantSettlementService.compareParticipantSettlement(o1, o2);

  compareDisbursement = (o1: IDisbursement | null, o2: IDisbursement | null): boolean =>
    this.disbursementService.compareDisbursement(o1, o2);

  compareRepayment = (o1: IRepayment | null, o2: IRepayment | null): boolean => this.repaymentService.compareRepayment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fTTransactionDetails }) => {
      this.fTTransactionDetails = fTTransactionDetails;
      if (fTTransactionDetails) {
        this.updateForm(fTTransactionDetails);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fTTransactionDetails = this.fTTransactionDetailsFormService.getFTTransactionDetails(this.editForm);
    if (fTTransactionDetails.id !== null) {
      this.subscribeToSaveResponse(this.fTTransactionDetailsService.update(fTTransactionDetails));
    } else {
      this.subscribeToSaveResponse(this.fTTransactionDetailsService.create(fTTransactionDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFTTransactionDetails>>): void {
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

  protected updateForm(fTTransactionDetails: IFTTransactionDetails): void {
    this.fTTransactionDetails = fTTransactionDetails;
    this.fTTransactionDetailsFormService.resetForm(this.editForm, fTTransactionDetails);

    this.participantSettlementsSharedCollection =
      this.participantSettlementService.addParticipantSettlementToCollectionIfMissing<IParticipantSettlement>(
        this.participantSettlementsSharedCollection,
        fTTransactionDetails.participantsettlement,
      );
    this.disbursementsSharedCollection = this.disbursementService.addDisbursementToCollectionIfMissing<IDisbursement>(
      this.disbursementsSharedCollection,
      fTTransactionDetails.disbursement,
    );
    this.repaymentsSharedCollection = this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(
      this.repaymentsSharedCollection,
      fTTransactionDetails.repayment,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.participantSettlementService
      .query()
      .pipe(map((res: HttpResponse<IParticipantSettlement[]>) => res.body ?? []))
      .pipe(
        map((participantSettlements: IParticipantSettlement[]) =>
          this.participantSettlementService.addParticipantSettlementToCollectionIfMissing<IParticipantSettlement>(
            participantSettlements,
            this.fTTransactionDetails?.participantsettlement,
          ),
        ),
      )
      .subscribe(
        (participantSettlements: IParticipantSettlement[]) => (this.participantSettlementsSharedCollection = participantSettlements),
      );

    this.disbursementService
      .query()
      .pipe(map((res: HttpResponse<IDisbursement[]>) => res.body ?? []))
      .pipe(
        map((disbursements: IDisbursement[]) =>
          this.disbursementService.addDisbursementToCollectionIfMissing<IDisbursement>(
            disbursements,
            this.fTTransactionDetails?.disbursement,
          ),
        ),
      )
      .subscribe((disbursements: IDisbursement[]) => (this.disbursementsSharedCollection = disbursements));

    this.repaymentService
      .query()
      .pipe(map((res: HttpResponse<IRepayment[]>) => res.body ?? []))
      .pipe(
        map((repayments: IRepayment[]) =>
          this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(repayments, this.fTTransactionDetails?.repayment),
        ),
      )
      .subscribe((repayments: IRepayment[]) => (this.repaymentsSharedCollection = repayments));
  }
}
