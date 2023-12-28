import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ITradeEntity } from '../trade-entity.model';
import { TradeEntityService } from '../service/trade-entity.service';
import { TradeEntityFormService, TradeEntityFormGroup } from './trade-entity-form.service';

@Component({
  standalone: true,
  selector: 'jhi-trade-entity-update',
  templateUrl: './trade-entity-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class TradeEntityUpdateComponent implements OnInit {
  isSaving = false;
  tradeEntity: ITradeEntity | null = null;

  editForm: TradeEntityFormGroup = this.tradeEntityFormService.createTradeEntityFormGroup();

  constructor(
    protected tradeEntityService: TradeEntityService,
    protected tradeEntityFormService: TradeEntityFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tradeEntity }) => {
      this.tradeEntity = tradeEntity;
      if (tradeEntity) {
        this.updateForm(tradeEntity);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tradeEntity = this.tradeEntityFormService.getTradeEntity(this.editForm);
    if (tradeEntity.id !== null) {
      this.subscribeToSaveResponse(this.tradeEntityService.update(tradeEntity));
    } else {
      this.subscribeToSaveResponse(this.tradeEntityService.create(tradeEntity));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITradeEntity>>): void {
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

  protected updateForm(tradeEntity: ITradeEntity): void {
    this.tradeEntity = tradeEntity;
    this.tradeEntityFormService.resetForm(this.editForm, tradeEntity);
  }
}
