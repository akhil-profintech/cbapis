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
import { IParticipantSettlement } from 'app/entities/participant-settlement/participant-settlement.model';
import { ParticipantSettlementService } from 'app/entities/participant-settlement/service/participant-settlement.service';
import { DocDetailsService } from '../service/doc-details.service';
import { IDocDetails } from '../doc-details.model';
import { DocDetailsFormService, DocDetailsFormGroup } from './doc-details-form.service';

@Component({
  standalone: true,
  selector: 'jhi-doc-details-update',
  templateUrl: './doc-details-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DocDetailsUpdateComponent implements OnInit {
  isSaving = false;
  docDetails: IDocDetails | null = null;

  disbursementsSharedCollection: IDisbursement[] = [];
  repaymentsSharedCollection: IRepayment[] = [];
  participantSettlementsSharedCollection: IParticipantSettlement[] = [];

  editForm: DocDetailsFormGroup = this.docDetailsFormService.createDocDetailsFormGroup();

  constructor(
    protected docDetailsService: DocDetailsService,
    protected docDetailsFormService: DocDetailsFormService,
    protected disbursementService: DisbursementService,
    protected repaymentService: RepaymentService,
    protected participantSettlementService: ParticipantSettlementService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareDisbursement = (o1: IDisbursement | null, o2: IDisbursement | null): boolean =>
    this.disbursementService.compareDisbursement(o1, o2);

  compareRepayment = (o1: IRepayment | null, o2: IRepayment | null): boolean => this.repaymentService.compareRepayment(o1, o2);

  compareParticipantSettlement = (o1: IParticipantSettlement | null, o2: IParticipantSettlement | null): boolean =>
    this.participantSettlementService.compareParticipantSettlement(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ docDetails }) => {
      this.docDetails = docDetails;
      if (docDetails) {
        this.updateForm(docDetails);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const docDetails = this.docDetailsFormService.getDocDetails(this.editForm);
    if (docDetails.id !== null) {
      this.subscribeToSaveResponse(this.docDetailsService.update(docDetails));
    } else {
      this.subscribeToSaveResponse(this.docDetailsService.create(docDetails));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocDetails>>): void {
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

  protected updateForm(docDetails: IDocDetails): void {
    this.docDetails = docDetails;
    this.docDetailsFormService.resetForm(this.editForm, docDetails);

    this.disbursementsSharedCollection = this.disbursementService.addDisbursementToCollectionIfMissing<IDisbursement>(
      this.disbursementsSharedCollection,
      docDetails.disbursement,
    );
    this.repaymentsSharedCollection = this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(
      this.repaymentsSharedCollection,
      docDetails.repayment,
    );
    this.participantSettlementsSharedCollection =
      this.participantSettlementService.addParticipantSettlementToCollectionIfMissing<IParticipantSettlement>(
        this.participantSettlementsSharedCollection,
        docDetails.participantsettlement,
      );
  }

  protected loadRelationshipsOptions(): void {
    this.disbursementService
      .query()
      .pipe(map((res: HttpResponse<IDisbursement[]>) => res.body ?? []))
      .pipe(
        map((disbursements: IDisbursement[]) =>
          this.disbursementService.addDisbursementToCollectionIfMissing<IDisbursement>(disbursements, this.docDetails?.disbursement),
        ),
      )
      .subscribe((disbursements: IDisbursement[]) => (this.disbursementsSharedCollection = disbursements));

    this.repaymentService
      .query()
      .pipe(map((res: HttpResponse<IRepayment[]>) => res.body ?? []))
      .pipe(
        map((repayments: IRepayment[]) =>
          this.repaymentService.addRepaymentToCollectionIfMissing<IRepayment>(repayments, this.docDetails?.repayment),
        ),
      )
      .subscribe((repayments: IRepayment[]) => (this.repaymentsSharedCollection = repayments));

    this.participantSettlementService
      .query()
      .pipe(map((res: HttpResponse<IParticipantSettlement[]>) => res.body ?? []))
      .pipe(
        map((participantSettlements: IParticipantSettlement[]) =>
          this.participantSettlementService.addParticipantSettlementToCollectionIfMissing<IParticipantSettlement>(
            participantSettlements,
            this.docDetails?.participantsettlement,
          ),
        ),
      )
      .subscribe(
        (participantSettlements: IParticipantSettlement[]) => (this.participantSettlementsSharedCollection = participantSettlements),
      );
  }
}
