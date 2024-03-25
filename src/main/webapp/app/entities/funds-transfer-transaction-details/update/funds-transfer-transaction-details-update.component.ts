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
import { FundsTransferTransactionDetailsService } from '../service/funds-transfer-transaction-details.service';
import { IFundsTransferTransactionDetails } from '../funds-transfer-transaction-details.model';
import {
  FundsTransferTransactionDetailsFormService,
  FundsTransferTransactionDetailsFormGroup,
} from './funds-transfer-transaction-details-form.service';

@Component({
  standalone: true,
  selector: 'jhi-funds-transfer-transaction-details-update',
  templateUrl: './funds-transfer-transaction-details-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FundsTransferTransactionDetailsUpdateComponent implements OnInit {
  isSaving = false;
  fundsTransferTransactionDetails: IFundsTransferTransactionDetails | null = null;

  participantSettlementsSharedCollection: IParticipantSettlement[] = [];
  disbursementsSharedCollection: IDisbursement[] = [];
  repaymentsSharedCollection: IRepayment[] = [];

  editForm: FundsTransferTransactionDetailsFormGroup =
    this.fundsTransferTransactionDetailsFormService.createFundsTransferTransactionDetailsFormGroup();

  constructor(
    protected fundsTransferTransactionDetailsService: FundsTransferTransactionDetailsService,
    protected fundsTransferTransactionDetailsFormService: FundsTransferTransactionDetailsFormService,
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
    this.activatedRoute.data.subscribe(({ fundsTransferTransactionDetails }) => {
      this.fundsTransferTransactionDetails = fundsTransferTransactionDetails;
      if (fundsTransferTransactionDetails) {
        this.updateForm(fundsTransferTransactionDetails);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fundsTransferTransactionDetails = this.fundsTransferTransactionDetailsFormService.getFundsTransferTransactionDetails(
      this.editForm,
    );
    if (fundsTransferTransactionDetails.id !== null) {
      this.subscribeToSaveResponse(this.fundsTransferTransactionDetailsService.update(fundsTransferTransactionDetails));
    } else {
      this.subscribeToSaveResponse(this.fundsTransferTransactionDetailsService.create(fundsTransferTransactionDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFundsTransferTransactionDetails>>): void {
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

  protected updateForm(fundsTransferTransactionDetails: IFundsTransferTransactionDetails): void {
    this.fundsTransferTransactionDetails = fundsTransferTransactionDetails;
    this.fundsTransferTransactionDetailsFormService.resetForm(this.editForm, fundsTransferTransactionDetails);

    this.participantSettlementsSharedCollection =
      this.participantSettlementService.addParticipantSettlementToCollectionIfMissing<IParticipantSettlement>(
        this.participantSettlementsSharedCollection,
        fundsTransferTransactionDetails.participantsettlement,
      );
    this.disbursementsSharedCollection = this.disbursementService.addDisbursementToCollectionIfMissing<IDisbursement>(
      this.disbursementsSharedCollection,
      fundsTransferTransactionDetails.disbursement,
    );
    this.repaymentsSharedCollection = this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(
      this.repaymentsSharedCollection,
      fundsTransferTransactionDetails.repayment,
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
            this.fundsTransferTransactionDetails?.participantsettlement,
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
            this.fundsTransferTransactionDetails?.disbursement,
          ),
        ),
      )
      .subscribe((disbursements: IDisbursement[]) => (this.disbursementsSharedCollection = disbursements));

    this.repaymentService
      .query()
      .pipe(map((res: HttpResponse<IRepayment[]>) => res.body ?? []))
      .pipe(
        map((repayments: IRepayment[]) =>
          this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(repayments, this.fundsTransferTransactionDetails?.repayment),
        ),
      )
      .subscribe((repayments: IRepayment[]) => (this.repaymentsSharedCollection = repayments));
  }
}
