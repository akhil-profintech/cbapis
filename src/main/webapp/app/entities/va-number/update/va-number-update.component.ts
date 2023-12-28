import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';
import { TradeEntityService } from 'app/entities/trade-entity/service/trade-entity.service';
import { IVANumber } from '../va-number.model';
import { VANumberService } from '../service/va-number.service';
import { VANumberFormService, VANumberFormGroup } from './va-number-form.service';

@Component({
  standalone: true,
  selector: 'jhi-va-number-update',
  templateUrl: './va-number-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class VANumberUpdateComponent implements OnInit {
  isSaving = false;
  vANumber: IVANumber | null = null;

  tradeEntitiesSharedCollection: ITradeEntity[] = [];

  editForm: VANumberFormGroup = this.vANumberFormService.createVANumberFormGroup();

  constructor(
    protected vANumberService: VANumberService,
    protected vANumberFormService: VANumberFormService,
    protected tradeEntityService: TradeEntityService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareTradeEntity = (o1: ITradeEntity | null, o2: ITradeEntity | null): boolean => this.tradeEntityService.compareTradeEntity(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vANumber }) => {
      this.vANumber = vANumber;
      if (vANumber) {
        this.updateForm(vANumber);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vANumber = this.vANumberFormService.getVANumber(this.editForm);
    if (vANumber.id !== null) {
      this.subscribeToSaveResponse(this.vANumberService.update(vANumber));
    } else {
      this.subscribeToSaveResponse(this.vANumberService.create(vANumber));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVANumber>>): void {
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

  protected updateForm(vANumber: IVANumber): void {
    this.vANumber = vANumber;
    this.vANumberFormService.resetForm(this.editForm, vANumber);

    this.tradeEntitiesSharedCollection = this.tradeEntityService.addTradeEntityToCollectionIfMissing<ITradeEntity>(
      this.tradeEntitiesSharedCollection,
      vANumber.tradeEntity,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.tradeEntityService
      .query()
      .pipe(map((res: HttpResponse<ITradeEntity[]>) => res.body ?? []))
      .pipe(
        map((tradeEntities: ITradeEntity[]) =>
          this.tradeEntityService.addTradeEntityToCollectionIfMissing<ITradeEntity>(tradeEntities, this.vANumber?.tradeEntity),
        ),
      )
      .subscribe((tradeEntities: ITradeEntity[]) => (this.tradeEntitiesSharedCollection = tradeEntities));
  }
}
