import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';
import { TradeEntityService } from 'app/entities/trade-entity/service/trade-entity.service';
import { IUpdateVA } from '../update-va.model';
import { UpdateVAService } from '../service/update-va.service';
import { UpdateVAFormService, UpdateVAFormGroup } from './update-va-form.service';

@Component({
  standalone: true,
  selector: 'jhi-update-va-update',
  templateUrl: './update-va-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class UpdateVAUpdateComponent implements OnInit {
  isSaving = false;
  updateVA: IUpdateVA | null = null;

  tradeEntitiesSharedCollection: ITradeEntity[] = [];

  editForm: UpdateVAFormGroup = this.updateVAFormService.createUpdateVAFormGroup();

  constructor(
    protected updateVAService: UpdateVAService,
    protected updateVAFormService: UpdateVAFormService,
    protected tradeEntityService: TradeEntityService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareTradeEntity = (o1: ITradeEntity | null, o2: ITradeEntity | null): boolean => this.tradeEntityService.compareTradeEntity(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ updateVA }) => {
      this.updateVA = updateVA;
      if (updateVA) {
        this.updateForm(updateVA);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const updateVA = this.updateVAFormService.getUpdateVA(this.editForm);
    if (updateVA.id !== null) {
      this.subscribeToSaveResponse(this.updateVAService.update(updateVA));
    } else {
      this.subscribeToSaveResponse(this.updateVAService.create(updateVA));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUpdateVA>>): void {
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

  protected updateForm(updateVA: IUpdateVA): void {
    this.updateVA = updateVA;
    this.updateVAFormService.resetForm(this.editForm, updateVA);

    this.tradeEntitiesSharedCollection = this.tradeEntityService.addTradeEntityToCollectionIfMissing<ITradeEntity>(
      this.tradeEntitiesSharedCollection,
      updateVA.tradeEntity,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.tradeEntityService
      .query()
      .pipe(map((res: HttpResponse<ITradeEntity[]>) => res.body ?? []))
      .pipe(
        map((tradeEntities: ITradeEntity[]) =>
          this.tradeEntityService.addTradeEntityToCollectionIfMissing<ITradeEntity>(tradeEntities, this.updateVA?.tradeEntity),
        ),
      )
      .subscribe((tradeEntities: ITradeEntity[]) => (this.tradeEntitiesSharedCollection = tradeEntities));
  }
}
