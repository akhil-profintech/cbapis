import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IFundsTransferTransactionDetails } from '../funds-transfer-transaction-details.model';

@Component({
  standalone: true,
  selector: 'jhi-funds-transfer-transaction-details-detail',
  templateUrl: './funds-transfer-transaction-details-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FundsTransferTransactionDetailsDetailComponent {
  @Input() fundsTransferTransactionDetails: IFundsTransferTransactionDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
