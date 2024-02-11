import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ITradeChannel } from '../trade-channel.model';
import { TradeChannelService } from '../service/trade-channel.service';
import { TradeChannelFormService, TradeChannelFormGroup } from './trade-channel-form.service';

@Component({
  standalone: true,
  selector: 'jhi-trade-channel-update',
  templateUrl: './trade-channel-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class TradeChannelUpdateComponent implements OnInit {
  isSaving = false;
  tradeChannel: ITradeChannel | null = null;

  editForm: TradeChannelFormGroup = this.tradeChannelFormService.createTradeChannelFormGroup();

  constructor(
    protected tradeChannelService: TradeChannelService,
    protected tradeChannelFormService: TradeChannelFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tradeChannel }) => {
      this.tradeChannel = tradeChannel;
      if (tradeChannel) {
        this.updateForm(tradeChannel);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tradeChannel = this.tradeChannelFormService.getTradeChannel(this.editForm);
    if (tradeChannel.id !== null) {
      this.subscribeToSaveResponse(this.tradeChannelService.update(tradeChannel));
    } else {
      this.subscribeToSaveResponse(this.tradeChannelService.create(tradeChannel));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITradeChannel>>): void {
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

  protected updateForm(tradeChannel: ITradeChannel): void {
    this.tradeChannel = tradeChannel;
    this.tradeChannelFormService.resetForm(this.editForm, tradeChannel);
  }
}
