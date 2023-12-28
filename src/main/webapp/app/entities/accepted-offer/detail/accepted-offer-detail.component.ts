import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IAcceptedOffer } from '../accepted-offer.model';

@Component({
  standalone: true,
  selector: 'jhi-accepted-offer-detail',
  templateUrl: './accepted-offer-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class AcceptedOfferDetailComponent {
  @Input() acceptedOffer: IAcceptedOffer | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
