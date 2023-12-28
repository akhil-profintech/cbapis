import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IAnchorTrader } from '../anchor-trader.model';
import { AnchorTraderService } from '../service/anchor-trader.service';
import { AnchorTraderFormService, AnchorTraderFormGroup } from './anchor-trader-form.service';

@Component({
  standalone: true,
  selector: 'jhi-anchor-trader-update',
  templateUrl: './anchor-trader-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AnchorTraderUpdateComponent implements OnInit {
  isSaving = false;
  anchorTrader: IAnchorTrader | null = null;

  editForm: AnchorTraderFormGroup = this.anchorTraderFormService.createAnchorTraderFormGroup();

  constructor(
    protected anchorTraderService: AnchorTraderService,
    protected anchorTraderFormService: AnchorTraderFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ anchorTrader }) => {
      this.anchorTrader = anchorTrader;
      if (anchorTrader) {
        this.updateForm(anchorTrader);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const anchorTrader = this.anchorTraderFormService.getAnchorTrader(this.editForm);
    if (anchorTrader.id !== null) {
      this.subscribeToSaveResponse(this.anchorTraderService.update(anchorTrader));
    } else {
      this.subscribeToSaveResponse(this.anchorTraderService.create(anchorTrader));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnchorTrader>>): void {
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

  protected updateForm(anchorTrader: IAnchorTrader): void {
    this.anchorTrader = anchorTrader;
    this.anchorTraderFormService.resetForm(this.editForm, anchorTrader);
  }
}
