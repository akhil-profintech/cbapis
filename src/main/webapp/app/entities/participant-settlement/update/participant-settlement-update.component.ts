import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ISettlement } from 'app/entities/settlement/settlement.model';
import { SettlementService } from 'app/entities/settlement/service/settlement.service';
import { SettlementType } from 'app/entities/enumerations/settlement-type.model';
import { ParticipantSettlementService } from '../service/participant-settlement.service';
import { IParticipantSettlement } from '../participant-settlement.model';
import { ParticipantSettlementFormService, ParticipantSettlementFormGroup } from './participant-settlement-form.service';

@Component({
  standalone: true,
  selector: 'jhi-participant-settlement-update',
  templateUrl: './participant-settlement-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ParticipantSettlementUpdateComponent implements OnInit {
  isSaving = false;
  participantSettlement: IParticipantSettlement | null = null;
  settlementTypeValues = Object.keys(SettlementType);

  settlementsSharedCollection: ISettlement[] = [];

  editForm: ParticipantSettlementFormGroup = this.participantSettlementFormService.createParticipantSettlementFormGroup();

  constructor(
    protected participantSettlementService: ParticipantSettlementService,
    protected participantSettlementFormService: ParticipantSettlementFormService,
    protected settlementService: SettlementService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareSettlement = (o1: ISettlement | null, o2: ISettlement | null): boolean => this.settlementService.compareSettlement(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ participantSettlement }) => {
      this.participantSettlement = participantSettlement;
      if (participantSettlement) {
        this.updateForm(participantSettlement);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const participantSettlement = this.participantSettlementFormService.getParticipantSettlement(this.editForm);
    if (participantSettlement.id !== null) {
      this.subscribeToSaveResponse(this.participantSettlementService.update(participantSettlement));
    } else {
      this.subscribeToSaveResponse(this.participantSettlementService.create(participantSettlement));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParticipantSettlement>>): void {
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

  protected updateForm(participantSettlement: IParticipantSettlement): void {
    this.participantSettlement = participantSettlement;
    this.participantSettlementFormService.resetForm(this.editForm, participantSettlement);

    this.settlementsSharedCollection = this.settlementService.addSettlementToCollectionIfMissing<ISettlement>(
      this.settlementsSharedCollection,
      participantSettlement.settlement,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.settlementService
      .query()
      .pipe(map((res: HttpResponse<ISettlement[]>) => res.body ?? []))
      .pipe(
        map((settlements: ISettlement[]) =>
          this.settlementService.addSettlementToCollectionIfMissing<ISettlement>(settlements, this.participantSettlement?.settlement),
        ),
      )
      .subscribe((settlements: ISettlement[]) => (this.settlementsSharedCollection = settlements));
  }
}
