import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';
import { TradeEntityService } from 'app/entities/trade-entity/service/trade-entity.service';
import { IFundsTransfer } from '../funds-transfer.model';
import { FundsTransferService } from '../service/funds-transfer.service';
import { FundsTransferFormService, FundsTransferFormGroup } from './funds-transfer-form.service';

@Component({
  standalone: true,
  selector: 'jhi-funds-transfer-update',
  templateUrl: './funds-transfer-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FundsTransferUpdateComponent implements OnInit {
  isSaving = false;
  fundsTransfer: IFundsTransfer | null = null;

  tradeEntitiesSharedCollection: ITradeEntity[] = [];

  editForm: FundsTransferFormGroup = this.fundsTransferFormService.createFundsTransferFormGroup();

  constructor(
    protected fundsTransferService: FundsTransferService,
    protected fundsTransferFormService: FundsTransferFormService,
    protected tradeEntityService: TradeEntityService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  compareTradeEntity = (o1: ITradeEntity | null, o2: ITradeEntity | null): boolean => this.tradeEntityService.compareTradeEntity(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fundsTransfer }) => {
      this.fundsTransfer = fundsTransfer;
      if (fundsTransfer) {
        this.updateForm(fundsTransfer);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fundsTransfer = this.fundsTransferFormService.getFundsTransfer(this.editForm);
    if (fundsTransfer.id !== null) {
      this.subscribeToSaveResponse(this.fundsTransferService.update(fundsTransfer));
    } else {
      this.subscribeToSaveResponse(this.fundsTransferService.create(fundsTransfer));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFundsTransfer>>): void {
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

  protected updateForm(fundsTransfer: IFundsTransfer): void {
    this.fundsTransfer = fundsTransfer;
    this.fundsTransferFormService.resetForm(this.editForm, fundsTransfer);

    this.tradeEntitiesSharedCollection = this.tradeEntityService.addTradeEntityToCollectionIfMissing<ITradeEntity>(
      this.tradeEntitiesSharedCollection,
      fundsTransfer.tradeEntity,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.tradeEntityService
      .query()
      .pipe(map((res: HttpResponse<ITradeEntity[]>) => res.body ?? []))
      .pipe(
        map((tradeEntities: ITradeEntity[]) =>
          this.tradeEntityService.addTradeEntityToCollectionIfMissing<ITradeEntity>(tradeEntities, this.fundsTransfer?.tradeEntity),
        ),
      )
      .subscribe((tradeEntities: ITradeEntity[]) => (this.tradeEntitiesSharedCollection = tradeEntities));
  }
}
