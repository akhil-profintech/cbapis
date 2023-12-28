import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';
import { TradeEntityService } from 'app/entities/trade-entity/service/trade-entity.service';
import { IBeneValidation } from '../bene-validation.model';
import { BeneValidationService } from '../service/bene-validation.service';
import { BeneValidationFormService, BeneValidationFormGroup } from './bene-validation-form.service';

@Component({
  standalone: true,
  selector: 'jhi-bene-validation-update',
  templateUrl: './bene-validation-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class BeneValidationUpdateComponent implements OnInit {
  isSaving = false;
  beneValidation: IBeneValidation | null = null;

  tradeEntitiesSharedCollection: ITradeEntity[] = [];

  editForm: BeneValidationFormGroup = this.beneValidationFormService.createBeneValidationFormGroup();

  constructor(
    protected beneValidationService: BeneValidationService,
    protected beneValidationFormService: BeneValidationFormService,
    protected tradeEntityService: TradeEntityService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareTradeEntity = (o1: ITradeEntity | null, o2: ITradeEntity | null): boolean => this.tradeEntityService.compareTradeEntity(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ beneValidation }) => {
      this.beneValidation = beneValidation;
      if (beneValidation) {
        this.updateForm(beneValidation);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const beneValidation = this.beneValidationFormService.getBeneValidation(this.editForm);
    if (beneValidation.id !== null) {
      this.subscribeToSaveResponse(this.beneValidationService.update(beneValidation));
    } else {
      this.subscribeToSaveResponse(this.beneValidationService.create(beneValidation));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBeneValidation>>): void {
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

  protected updateForm(beneValidation: IBeneValidation): void {
    this.beneValidation = beneValidation;
    this.beneValidationFormService.resetForm(this.editForm, beneValidation);

    this.tradeEntitiesSharedCollection = this.tradeEntityService.addTradeEntityToCollectionIfMissing<ITradeEntity>(
      this.tradeEntitiesSharedCollection,
      beneValidation.tradeEntity,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.tradeEntityService
      .query()
      .pipe(map((res: HttpResponse<ITradeEntity[]>) => res.body ?? []))
      .pipe(
        map((tradeEntities: ITradeEntity[]) =>
          this.tradeEntityService.addTradeEntityToCollectionIfMissing<ITradeEntity>(tradeEntities, this.beneValidation?.tradeEntity),
        ),
      )
      .subscribe((tradeEntities: ITradeEntity[]) => (this.tradeEntitiesSharedCollection = tradeEntities));
  }
}
