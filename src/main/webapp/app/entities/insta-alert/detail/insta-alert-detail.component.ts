import { Component, Input } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IInstaAlert } from '../insta-alert.model';

@Component({
  standalone: true,
  selector: 'jhi-insta-alert-detail',
  templateUrl: './insta-alert-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class InstaAlertDetailComponent {
  @Input() instaAlert: IInstaAlert | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  previousState(): void {
    window.history.back();
  }
}
