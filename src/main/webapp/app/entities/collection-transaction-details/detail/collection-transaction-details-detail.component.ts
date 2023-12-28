import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { ICollectionTransactionDetails } from '../collection-transaction-details.model';

@Component({
  standalone: true,
  selector: 'jhi-collection-transaction-details-detail',
  templateUrl: './collection-transaction-details-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CollectionTransactionDetailsDetailComponent {
  @Input() collectionTransactionDetails: ICollectionTransactionDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
